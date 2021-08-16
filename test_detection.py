import cv2, math, os
import matplotlib.pyplot as plt
from api.model.palo import Palo

def crop_image_header_footer(image_path):
	OUTPUT_DIR = '/home/cobaia/Desktop/frite/api/tests/v2/crop_header_footer'
	basename = os.path.basename(image_path)
	image = cv2.imread(image_path)

	WIDTH = image.shape[1]
	TOP_CROP = 220
	BOTTOM_CROP = 2050
	crop_image = image[TOP_CROP:BOTTOM_CROP, 0:WIDTH]

	cv2.imwrite(OUTPUT_DIR + '/' + basename, crop_image)

def process_image(image):
	dst = cv2.fastNlMeansDenoisingColored(image,None,10,10,7,21)
	gray = cv2.cvtColor(dst, cv2.COLOR_BGR2GRAY)
	blur = cv2.GaussianBlur(gray,(5,5),0)
	thresh = cv2.threshold(blur, 0, 255, cv2.THRESH_BINARY_INV + cv2.THRESH_OTSU)[1]
	return thresh
	# cv2.imwrite('thresh.png', thresh)

def checkBorderXY(x, y):
	if x > 30 and y > 20:
		return True
	else:
		return False

def calculate_distance(x1,y1,x2,y2):
    return math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

def calculate_area(w, h):
	return w * h

def calculate_mm(pixels):
	DPI = 96
	return (pixels * 25.4) / DPI

def crop_image(image, image_name, sort_list, HEIGHT, WIDTH):
	horizontal_lines = []
	for palo in sort_list:
		if palo.w > palo.h:
			horizontal_lines.append(palo)

	train_line = max(horizontal_lines, key=lambda x: x.area)
	crop_line = train_line.y+train_line.h-20

	crop_image = image[crop_line:HEIGHT, 0:WIDTH]
	cv2.imwrite('{}/{}.jpg'.format('/home/cobaia/Desktop/frite/api/tests/v2/crop', image_name), crop_image)
	#cv2.imwrite(OUTPUT_DIR + '/' + image_, crop_image)

def sort_contours(cnts, method="left-to-right"):
	reverse = False
	i = 0
	if method == "right-to-left" or method == "bottom-to-top":
		reverse = True

	if method == "top-to-bottom" or method == "bottom-to-top":
		i = 1

	boundingBoxes = [cv2.boundingRect(c) for c in cnts]
	(cnts, boundingBoxes) = zip(*sorted(zip(cnts, boundingBoxes),
		key=lambda b:b[1][i], reverse=reverse))

	return (cnts, boundingBoxes)

def contour_filter(thresh, HEIGHT, WIDTH):
	cnts = cv2.findContours(thresh, cv2.RETR_LIST,cv2.CHAIN_APPROX_NONE)
	cnts = cnts[0] if len(cnts) == 2 else cnts[1]
	(cnts, boundingBoxes) = sort_contours(cnts, method="top-to-bottom")
	#cv2.drawContours()
	palos_list = []

	for c in cnts:
		x,y,w,h = cv2.boundingRect(c)
		if checkBorderXY(x, y) and h < 500 and y < HEIGHT - 20:
			base = calculate_area(w, h)
			if base > 200:

				M = cv2.moments(c)
				cX = int(M["m10"] / M["m00"])
				cY = int(M["m01"] / M["m00"])

				p = Palo(None, x, y, w, h, base, cX, cY, x + w, y + h, calculate_distance(0,0, cX, cY), calculate_distance(cX, cY, 0, cY), calculate_distance(cX, cY, WIDTH, cY))
				if p.margin_right > 21 or p.margin_left > 21:
					palos_list.append(p)

	return palos_list

def sort_contours_left_to_right(palos_list):
	distance_list = palos_list
	distance_list.sort(key=lambda x: x.distance, reverse=False)
	sort_list = []

	for palo in palos_list:
		if palo.distance == distance_list[0].distance: # verificacao para ser o primeiro da lista ordenada
			#print(palo.distance)
			sort_list.append(palo)
			break

	for j in range(0, len(palos_list)):
		minor_distance = 0
		next_palo = 0
		for palo in range(len(palos_list)):
			if palos_list[palo] in sort_list:
				pass

			else:
				palo_distance = calculate_distance(palos_list[palo].cX, palos_list[palo].cY, sort_list[j].cX, sort_list[j].cY)
				if minor_distance == 0:
					minor_distance = palo_distance
					next_palo = palos_list[palo]

				else:
					if minor_distance > palo_distance:
						minor_distance = palo_distance
						next_palo = palos_list[palo]
		
		if type(next_palo) == int:
			break

		sort_list.append(next_palo)

	return sort_list

def set_rows(sort_list):
	row_list = []
	row = []
	for xpto in range(len(sort_list)):
		row.append(sort_list[xpto])
		try:
			if sort_list[xpto].y / sort_list[xpto+1].y < 0.88 and sort_list[xpto+1].h > sort_list[xpto+1].w:
				row_list.append(row)
				#print('quebra de linha na posicao {} | QUANTIDADE: {}'.format(xpto, len(row)))
				row = []
		except:
			row_list.append(row)
			#print('quebra de linha na posicao {} | QUANTIDADE: {}'.format(xpto, len(row)))
			break

	return row_list

def set_palographic_test(row_list, image, image_name, FONT, FONT_SCALE, COLOR, THICKNESS):
	palographic_test = []
	palo_counter = 0
	for a in row_list:
		a.sort(key=lambda x: x.margin_left, reverse=False)
		for palo in range(len(a)):
			cv2.circle(image, (a[palo].x, a[palo].y), 7, (255, 0, 255), -1) #top
			cv2.circle(image, (a[palo].x + a[palo].w, a[palo].y + a[palo].h), 7, (0, 0, 255), -1) #base
			cv2.circle(image, (a[palo].cX, a[palo].cY), 5, (255, 0, 0), -1) #centroid
			cv2.rectangle(image, (a[palo].x, a[palo].y), (a[palo].x + a[palo].w, a[palo].y + a[palo].h), (36,255,12), 2)
			cv2.putText(image, str(palo_counter), (a[palo].x, a[palo].y), FONT, FONT_SCALE, COLOR, THICKNESS, cv2.LINE_AA, False)
			print('{}: top: ({},{}) | width: {} | height: {} | centroid: ({},{}) | base: ({},{}) | margin-left: {} | margin-right: {} | area: {}'
				.format(palo_counter, a[palo].x, a[palo].y, a[palo].w, a[palo].h, 
				a[palo].cX, a[palo].cY, a[palo].x + a[palo].w, a[palo].y + a[palo].h, 
				a[palo].margin_left, a[palo].margin_right, a[palo].area))
				#print(sort_list[palo].distance)
			a[palo].roi = palo_counter
			palo_counter += 1
			palographic_test.append(a[palo])

	cv2.imwrite(f'/home/cobaia/Desktop/frite/api/tests/v2/sof/{image_name}.jpg', image)
	return palographic_test

# def set_palographic_test_test(row_list):
# 	palographic_test = []
# 	palo_counter = 0
# 	for a in row_list:
# 		a.sort(key=lambda x: x.margin_left, reverse=False)
# 		for palo in range(len(a)):
# 			try:
# 				if a[palo+1].x - a[palo].x <= 10 and a[palo+1].y < a[palo].y:
# 					palo1 = [a[palo].x, a[palo].y, a[palo].w, a[palo].h]
# 					palo2 = [a[palo+1].x, a[palo+1].y, a[palo+1].w, a[palo+1].h]
# 					x, y, w, h = rect_union(palo1, palo2)
# 					area = calculate_area(w, h)

# 					p = Palo(None, x, y, w, h, area, None, None, x + w, y + h, None, None, None)
# 					cv2.rectangle(image, (p.x, p.y), (p.x + p.w, p.y + p.h), (36,255,12), 2)
# 					cv2.putText(image, str(palo_counter), (p.x, p.y), font, fontScale, color, thickness, cv2.LINE_AA, False)

# 					print('try,if | {}: top: ({},{}) | width: {} | height: {} | centroid: ({},{}) | base: ({},{}) | margin-left: {} | margin-right: {} | area: {}'
# 						.format(palo_counter, p.x, p.y, p.w, p.h, 
# 						p.cX, p.cY, p.x + p.w, p.y + p.h, 
# 						p.margin_left, p.margin_right, p.area))
# 						#print(sort_list[palo].distance)

# 					#palo 
# 					p.roi = palo_counter
# 					palo_counter += 1
# 					palographic_test.append(p)
# 					a.pop(palo+1)

# 				else:
# 					cv2.circle(image, (a[palo].x, a[palo].y), 7, (255, 0, 255), -1) #top
# 					cv2.circle(image, (a[palo].x + a[palo].w, a[palo].y + a[palo].h), 7, (0, 0, 255), -1) #base
# 					cv2.circle(image, (a[palo].cX, a[palo].cY), 5, (255, 0, 0), -1) #centroid
# 					cv2.rectangle(image, (a[palo].x, a[palo].y), (a[palo].x + a[palo].w, a[palo].y + a[palo].h), (36,255,12), 2)
# 					cv2.putText(image, str(palo_counter), (a[palo].x, a[palo].y), font, fontScale, color, thickness, cv2.LINE_AA, False)
# 					print('try,else,else | {}: top: ({},{}) | width: {} | height: {} | centroid: ({},{}) | base: ({},{}) | margin-left: {} | margin-right: {} | area: {}'
# 						.format(palo_counter, a[palo].x, a[palo].y, a[palo].w, a[palo].h, 
# 						a[palo].cX, a[palo].cY, a[palo].x + a[palo].w, a[palo].y + a[palo].h, 
# 						a[palo].margin_left, a[palo].margin_right, a[palo].area))
# 						#print(sort_list[palo].distance)
# 					a[palo].roi = palo_counter
# 					palo_counter += 1
# 					palographic_test.append(a[palo])

# 			except:
# 				try:
# 					cv2.circle(image, (a[palo].x, a[palo].y), 7, (255, 0, 255), -1) #top
# 					cv2.circle(image, (a[palo].x + a[palo].w, a[palo].y + a[palo].h), 7, (0, 0, 255), -1) #base
# 					cv2.circle(image, (a[palo].cX, a[palo].cY), 5, (255, 0, 0), -1) #centroid
# 					cv2.rectangle(image, (a[palo].x, a[palo].y), (a[palo].x + a[palo].w, a[palo].y + a[palo].h), (36,255,12), 2)
# 					cv2.putText(image, str(palo_counter), (a[palo].x, a[palo].y), font, fontScale, color, thickness, cv2.LINE_AA, False)
# 					print('except | {}: top: ({},{}) | width: {} | height: {} | centroid: ({},{}) | base: ({},{}) | margin-left: {} | margin-right: {} | area: {}'
# 						.format(palo_counter, a[palo].x, a[palo].y, a[palo].w, a[palo].h, 
# 						a[palo].cX, a[palo].cY, a[palo].x + a[palo].w, a[palo].y + a[palo].h, 
# 						a[palo].margin_left, a[palo].margin_right, a[palo].area))
# 						#print(sort_list[palo].distance)
# 					a[palo].roi = palo_counter
# 					palo_counter += 1
# 					palographic_test.append(a[palo])
# 				except:
# 					pass
			

	return palographic_test

def set_intervals(palographic_test):
	intervals = []
	intervals_list = []
	for palo in palographic_test:
		if palo.h > palo.w:
			intervals.append(palo)
		else:
			intervals_list.append(intervals)
			intervals = []

	return intervals_list

def get_sum_diff(intervals_list):
	diff_list = []
	for i in range(len(intervals_list)):
		try:
			diff = len(intervals_list[i+1]) - len(intervals_list[i])
			if diff < 0:
				diff = diff * - 1
			diff_list.append(diff)
		except:
			break

	return sum(diff_list)

def calculate_nor(sum_diff, total_palos):
	return (sum_diff * 100) / total_palos

def get_total_palos(intervals_list):
	count_palos = []
	for i in intervals_list:
		count_palos.append(len(i))
	return sum(count_palos)

def set_plot_yield(intervals_list):
	palos_per_interval = {}
	for i in range(len(intervals_list)):
		palos_per_interval.update({i+1: len(intervals_list[i])})

	count_intervals = list(palos_per_interval.keys())
	count_intervals_palos = list(palos_per_interval.values())

	plt.plot(count_intervals, count_intervals_palos)
	plt.title('Quantidade de Palos por Intervalo')
	plt.xlabel('Intervalo')
	plt.ylabel('Palos')
	plt.show()

	return palos_per_interval

def get_palos_size(intervals_list):
	size_palos = {}
	ma_palo = []
	mi_palo = []
	for interval in range(len(intervals_list)):
		major_palo = 0
		minor_palo = 0
		for palo in intervals_list[interval]:
			if major_palo == 0 and minor_palo == 0:
				major_palo = palo
				minor_palo = palo

			elif palo.h > major_palo.h:
				major_palo = palo

			elif palo.h < minor_palo.h:
				minor_palo = palo
			
			else:
				pass

		ma_palo.append(major_palo)
		mi_palo.append(minor_palo)
		print('intervalo {} - maior: {} | tamanho: {}'.format(interval+1, major_palo.roi, calculate_mm(major_palo.h)))
		print('intervalo {} - menor: {} | tamanho: {}'.format(interval+1, minor_palo.roi, calculate_mm(minor_palo.h)))


	size_palos.update({'major': ma_palo, 'minor': mi_palo})
	return size_palos

def get_major_palo_test(size_palos):
	palo = max(size_palos['major'], key=lambda x: calculate_mm(x.h))
	return palo

def get_minor_palo_test(size_palos):
	palo = min(size_palos['minor'], key=lambda x: calculate_mm(x.h))
	return palo

def get_size_palos_interval(size_palos):
	sum_major = (sum(map(lambda x: calculate_mm(x.h), size_palos['major']))) / 5
	sum_minor = (sum(map(lambda x: calculate_mm(x.h), size_palos['minor']))) / 5
	return (sum_major + sum_minor) / 2

def rect_union(a,b):
	x = min(a[0], b[0])
	y = min(a[1], b[1])
	w = max(a[0]+a[2], b[0]+b[2]) - x
	h = max(a[1]+a[3], b[1]+b[3]) - y
	return (x, y, w, h)

def set_rect_centroid(x1, x2, y1, y2):
	return (int((x1+x2)/2), int((y1+y2)/2))

#HEIGHT = image.shape[0]
# image_name = 'IMG_6971.jpg'

# image = cv2.imread('api/tests/pencil/scanner/{}'.format(image_name))
# thresh = process_image(image)


# font = cv2.FONT_HERSHEY_SIMPLEX
# fontScale = 1
# color = (255, 0, 0)
# thickness = 2

# palos_list = contour_filter(thresh)
# sort_list = sort_contours_left_to_right(palos_list)
# crop_image(image_name, sort_list)

# print('crop image')

# image = cv2.imread('{}'.format(image_name))
# thresh = process_image(image)

# HEIGHT = image.shape[0]
# WIDTH = image.shape[1]
# font = cv2.FONT_HERSHEY_SIMPLEX
# fontScale = 1
# color = (255, 0, 0)
# thickness = 2

# print('Altura da imagem: {} | Largura da imagem: {}'.format(HEIGHT, WIDTH))


# palos_list = contour_filter(thresh)
# sort_list = sort_contours_left_to_right(palos_list)
# row_list = set_rows(sort_list)
# palographic_test = set_palographic_test(row_list)


# intervals_list = set_intervals(palographic_test)

# size_palos = get_palos_size(intervals_list)

# size_palos_interval = get_size_palos_interval(size_palos)
# impulsivity = get_major_palo_test(size_palos).h - get_minor_palo_test(size_palos).h

# print('impulsividade: {}'.format(impulsivity))

# palos_per_interval = set_plot_yield(intervals_list)
# print(palos_per_interval)

# sum_diff = get_sum_diff(intervals_list)
# total_palos = get_total_palos(intervals_list)

# print('total de palos: {}'.format(total_palos))
# print('soma das diferencas: {}'.format(sum_diff))
# print('NOR: {}'.format(calculate_nor(sum_diff, total_palos)))

#cv2.imwrite('api/tests/scanner/tmp/sof_crop_{}'.format(image_name), image)










#cv2.putText(image, ROI_number, (x, y), font, fontScale, color, thickness, cv2.LINE_AA, False)

# ROI_number = 0
# area_list = []
# for c in cnts:
# 	x,y,w,h = cv2.boundingRect(c)
# 	if checkBorderXY(x, y):
# 		M = cv2.moments(c)
# 		if M["m00"] == 0:
# 			pass
# 		else:
# 			cX = int(M["m10"] / M["m00"])
# 			cY = int(M["m01"] / M["m00"])

# 		area = calculate_area(w, h)
# 		if area > 300 and h < 500:
# 			print('ROI: {} | Top: ({},{}) | Width: {} | Height: {} | Centroid: ({},{}) | Base: ({},{}) | Area: {}'.format(ROI_number, x, y, w, h, cX, cY, x + w, y + h, area))
# 			cv2.circle(image, (x, y), 7, (255, 0, 255), -1) #top
# 			cv2.circle(image, (x + w, y + h), 7, (0, 0, 255), -1) #base
# 			cv2.circle(image, (cX, cY), 5, (255, 0, 0), -1) #centroid
# 			cv2.rectangle(image, (x, y), (x + w, y + h), (36,255,12), 2)
# 			# cv2.line(image, (cX, cY), (0, cY), (204, 102, 0), 4) #line centroid | margin-left
# 			# cv2.line(image, (cX, cY), (image.shape[1], cY), (204, 102, 0), 4) #line centroid | margin-right
# 			# cv2.line(image, (x + w, y + h), (image.shape[1], y + h), (204, 102, 0), 4) #line base
# 			cv2.putText(image, str(ROI_number), (x, y), font, fontScale, color, thickness, cv2.LINE_AA, False)
# 			ROI_number += 1
# 			# ROI = original[y:y+h, x:x+w]
# 			# cv2.imwrite('tests/scanner/tmp/sof/ROI_{}.png'.format(ROI_number), ROI)
# 			area_list.append(area)

# median_area = int(np.median(area_list))
# print(median_area)


#cv2.circle(image, (palo.x, palo.y), 7, (255, 0, 255), -1) #top
#break

# for i in palos_list:
# 	print('ROI: {} | Top: ({},{}) | Width: {} | Height: {} | Centroid: ({},{}) | Base: ({},{})'.format(ROI_number, i.x, i.y, i.w, i.h, i.cX, i.cY, i.x + i.w, i.y + i.h))
# 	cv2.circle(image, (i.x, i.y), 7, (255, 0, 255), -1) #top
# 	cv2.circle(image, (i.x + i.w, i.y + i.h), 7, (0, 0, 255), -1) #base
# 	cv2.circle(image, (i.cX, i.cY), 5, (255, 0, 0), -1) #centroid
# 	cv2.rectangle(image, (i.x, i.y), (i.x + i.w, i.y + i.h), (36,255,12), 2)
# 	cv2.putText(image, str(ROI_number), (i.x, i.y), font, fontScale, color, thickness, cv2.LINE_AA, False)
# 	ROI_number += 1

# p_list = []
# for p in palos_list:
# 	if p.h > p.w:
# 		p_list.append(p)

# # height_list = []
# matriz_topo = []
# matriz_centroid = []
# matriz_base = []
# for i in p_list:
# 	matriz_topo.append([p.x, p.y])
# 	matriz_centroid.append([p.cX, p.cY])
# 	matriz_base.append([p.baseX, p.baseY])


# print(matriz_topo)
# print(matriz_centroid)
# print(matriz_base)

# median_height = int(np.median(height_list))
# max_height = max(height_list)
# min_height = min(height_list)
		

# cv2.imwrite('crop_image.jpg', img_crop)