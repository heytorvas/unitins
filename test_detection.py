import cv2, math
import numpy as np
from api.model.palo import Palo

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

def calculate_distance(x1,y1,x2,y2):
     dist = math.sqrt((x2 - x1)**2 + (y2 - y1)**2)
     return dist

# Load image, grayscale, Otsu's threshold
image_name = 'pencil-without_flash-normal_quality-normal_size.jpg'
image = cv2.imread('api/tests/pencil/scanner/{}'.format(image_name))
original = image.copy()
dst = cv2.fastNlMeansDenoisingColored(image,None,10,10,7,21)
gray = cv2.cvtColor(dst, cv2.COLOR_BGR2GRAY)
blur = cv2.GaussianBlur(gray,(5,5),0)
thresh = cv2.threshold(blur, 0, 255, cv2.THRESH_BINARY_INV + cv2.THRESH_OTSU)[1]
cv2.imwrite('thresh.png', thresh)

WIDTH = image.shape[1]

# Find contours, obtain bounding box, extract and save ROI
ROI_number = 0
cnts = cv2.findContours(thresh, cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)
cnts = cnts[0] if len(cnts) == 2 else cnts[1]

(cnts, boundingBoxes) = sort_contours(cnts, method="top-to-bottom")

font = cv2.FONT_HERSHEY_SIMPLEX
fontScale = 1
color = (255, 0, 0)
thickness = 2

#cv2.putText(image, ROI_number, (x, y), font, fontScale, color, thickness, cv2.LINE_AA, False)

def checkBorderXY(x, y):
	coord = [x, y]
	return all(i > 20 for i in coord)

palos_list = []

for c in cnts:
	x,y,w,h = cv2.boundingRect(c)
	if w > 7 and h > 7:
		if checkBorderXY(x, y):
			if h < 500:
				# if h > w:
				# 	print('ROI: {} | X: {} | Y: {} | Width: {} | Height: {} | Position: vertical'.format(ROI_number, x, y, w, h))
				# else:
				# 	print('ROI: {} | X: {} | Y: {} | Width: {} | Height: {} | Position: horizontal'.format(ROI_number, x, y, w, h))

				M = cv2.moments(c)
				cX = int(M["m10"] / M["m00"])
				cY = int(M["m01"] / M["m00"])

				#p = Palo(x, y, w, h, cX, cY, x + w, y + h, calculate_distance(0,0, cX, cY))
				p = Palo(x, y, w, h, cX, cY, x + w, y + h, calculate_distance(0,0, cX, cY), calculate_distance(cX, cY, 0, cY), calculate_distance(cX, cY, image.shape[1], cY))
				palos_list.append(p)

				# print('ROI: {} | Top: ({},{}) | Width: {} | Height: {} | Centroid: ({},{}) | Base: ({},{})'.format(ROI_number, x, y, w, h, cX, cY, x + w, y + h))
				# cv2.circle(image, (x, y), 7, (255, 0, 255), -1) #top
				# cv2.circle(image, (x + w, y + h), 7, (0, 0, 255), -1) #base
				# cv2.circle(image, (cX, cY), 5, (255, 0, 0), -1) #centroid
				# cv2.rectangle(image, (x, y), (x + w, y + h), (36,255,12), 2)
				# cv2.line(image, (cX, cY), (0, cY), (204, 102, 0), 4) #line centroid | margin-left
				# cv2.line(image, (cX, cY), (image.shape[1], cY), (204, 102, 0), 4) #line centroid | margin-right
				# cv2.line(image, (x + w, y + h), (image.shape[1], y + h), (204, 102, 0), 4) #line base
				# cv2.putText(image, str(ROI_number), (x, y), font, fontScale, color, thickness, cv2.LINE_AA, False)

				# ROI = original[y:y+h, x:x+w]
				# cv2.imwrite('tests/scanner/tmp/sof/ROI_{}.png'.format(ROI_number), ROI)
				# ROI_number += 1

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

	#print('{},{}'.format(next_palo.x, next_palo.y))
	sort_list.append(next_palo)
	
	if type(next_palo) == int:
		break

# print(len(sort_list))

print('------------------------------------')

palographic_test = []
row = []
for xpto in range(len(sort_list)):
	row.append(sort_list[xpto])
	try:
		if sort_list[xpto].y / sort_list[xpto+1].y < 0.88 and sort_list[xpto+1].h > sort_list[xpto+1].w:
			print('quebra de linha na posicao {}'.format(xpto))
			palographic_test.append(row)
			row = []
	except:
		palographic_test.append(row)
		break

palo_counter = 0
for a in palographic_test:
	a.sort(key=lambda x: x.margin_left, reverse=False)
	for palo in range(len(a)):
		cv2.rectangle(image, (a[palo].x, a[palo].y), (a[palo].x + a[palo].w, a[palo].y + a[palo].h), (36,255,12), 2)
		cv2.putText(image, str(palo_counter), (a[palo].x, a[palo].y), font, fontScale, color, thickness, cv2.LINE_AA, False)
		print('{}: top: ({},{}) | width: {} | height: {} | centroid: ({},{}) | base: ({},{}) | margin-right: {} | margin-left: {}'
			.format(palo, a[palo].x, a[palo].y, a[palo].w, a[palo].h, 
			a[palo].cX, a[palo].cY, a[palo].x + a[palo].w, a[palo].y + a[palo].h, 
			a[palo].margin_left, a[palo].margin_right))
			#print(sort_list[palo].distance)
		palo_counter += 1


#cv2.circle(image, (palo.x, palo.y), 7, (255, 0, 255), -1) #top
#break



# 

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
		
cv2.imwrite('api/tests/scanner/tmp/sof_{}'.format(image_name), image)
# cv2.imwrite('crop_image.jpg', img_crop)