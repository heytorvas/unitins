import cv2, os, math
from model.palo import Palo

def resize_image(path):
    OUTPUT_DIR = '/home/cobaia/Desktop/frite/detection/images/resize'
    basename = os.path.basename(path)
    img = cv2.imread(path, cv2.IMREAD_UNCHANGED)
    width = img.shape[1]
    height = 2250
    dim = (width, height)
 
    resized = cv2.resize(img, dim, interpolation = cv2.INTER_AREA)
    cv2.imwrite(OUTPUT_DIR + '/' + basename, resized)

def crop_image_header_footer(image_path):
	OUTPUT_DIR = '/home/cobaia/Desktop/frite/detection/images/crop_header_footer'
	basename = os.path.basename(image_path)
	image = cv2.imread(image_path)

	width = image.shape[1]
	TOP_CROP = 220
	BOTTOM_CROP = 2000
	crop_image = image[TOP_CROP:BOTTOM_CROP, 0:width]

	cv2.imwrite(OUTPUT_DIR + '/' + basename, crop_image)

def process_image(image):
	dst = cv2.fastNlMeansDenoisingColored(image,None,10,10,7,21)
	gray = cv2.cvtColor(dst, cv2.COLOR_BGR2GRAY)
	blur = cv2.GaussianBlur(gray,(5,5),0)
	thresh = cv2.threshold(blur, 0, 255, cv2.THRESH_BINARY_INV + cv2.THRESH_OTSU)[1]
	return thresh

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

def checkBorderXY(x, y):
	if x > 30 and y > 20:
		return True
	else:
		return False

def calculate_area(w, h):
	return w * h

def calculate_distance(x1,y1,x2,y2):
    return math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

def contour_filter(thresh, HEIGHT, WIDTH):
	cnts = cv2.findContours(thresh, cv2.RETR_LIST,cv2.CHAIN_APPROX_NONE)
	cnts = cnts[0] if len(cnts) == 2 else cnts[1]
	(cnts, boundingBoxes) = sort_contours(cnts, method="top-to-bottom")
	palos_list = []

	for c in cnts:
		x,y,w,h = cv2.boundingRect(c)
		if checkBorderXY(x, y) and h < 500 and y < HEIGHT - 15:
			base = calculate_area(w, h)
			if base > 200:

				M = cv2.moments(c)
				cX = int(M["m10"] / M["m00"])
				cY = int(M["m01"] / M["m00"])

				p = Palo(None, x, y, w, h, base, cX, cY, x + w, y + h, calculate_distance(0,0, cX, cY), calculate_distance(cX, cY, 0, cY), calculate_distance(cX, cY, WIDTH, cY), None, None)
				if p.margin_right > 21 or p.margin_left > 21:
					palos_list.append(p)

	return palos_list

def crop_image(path):
	OUTPUT_DIR = '/home/cobaia/Desktop/frite/detection/images/crop'
	basename = os.path.basename(path)
	image = cv2.imread(path)

	height = image.shape[0]
	width = image.shape[1]

	########### MIDDLE LINE ###########
	thresh = process_image(image)
	palos_list = contour_filter(thresh, height, width)
	sort_list = sort_contours_left_to_right(palos_list)

	horizontal_lines = []
	for palo in sort_list:
		if palo.w > palo.h:
			horizontal_lines.append(palo)

	train_line = max(horizontal_lines, key=lambda x: x.area)
	crop_line = train_line.y+train_line.h-20
	crop_image = image[crop_line:height, 0:width]


	########### BLANK SPACE ###########
	thresh = process_image(crop_image)
	palos_list = contour_filter(thresh, height, width)
	sort_list = sort_contours_left_to_right(palos_list)
	row_list = set_rows(sort_list)
	test_palo = set_palographic_test(row_list)
	last_palo = test_palo[len(test_palo)-1]

	crop_image_blank = crop_image[:(last_palo.y+last_palo.h)+150,30:width-15]	
	cv2.imwrite(OUTPUT_DIR + '/' + basename, crop_image_blank)

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
				row = []
		except:
			row_list.append(row)
			break

	return row_list

def set_palographic_test(row_list):
	palographic_test = []
	palo_counter = 0
	for a in row_list:
		a.sort(key=lambda x: x.margin_left, reverse=False)
		for palo in range(len(a)):
			palographic_test.append(a[palo])

	return palographic_test
