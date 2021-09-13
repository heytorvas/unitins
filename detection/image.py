import cv2, os
from ordination import check_palo

def resize_image(path):
	OUTPUT_DIR = '/home/cobaia/Desktop/frite/detection/images/resize'
	basename = os.path.basename(path)
	img = cv2.imread(path, cv2.IMREAD_UNCHANGED)

	height = 2250
	width = img.shape[1]
	if img.shape[1] < 1300:
		width = img.shape[1] * 2
	
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

def crop_image(path):
    OUTPUT_DIR = '/home/cobaia/Desktop/frite/detection/images/crop'
    basename = os.path.basename(path)
    image = cv2.imread(path)

    ########### MIDDLE LINE ###########
    height = image.shape[0]
    width = image.shape[1]
    thresh = process_image(image)
    cnts = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    cnts = cnts[0] if len(cnts) == 2 else cnts[1]

    rect_list = []
    for c in cnts:
        x,y,w,h = cv2.boundingRect(c)
        rect_list.append((x, y, w, h))

    def middle(aux):
        return aux[2]

    middle_line = max(rect_list, key=middle)
    crop_line = middle_line[1] + middle_line[3]-7
    crop_image = image[crop_line:height, 0:width]

    ########### BLANK SPACE ###########
    height = crop_image.shape[0]
    width = crop_image.shape[1]
    thresh = process_image(crop_image)

    cnts = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    cnts = cnts[0] if len(cnts) == 2 else cnts[1]

    rect_list = []
    for c in cnts:
        x,y,w,h = cv2.boundingRect(c)
        if checkBorderXY(x, y):
            if check_palo(x, y, w, h):
                rect_list.append((x, y, w, h))

    def last(aux):
        return aux[1]

    last_palo = max(rect_list, key=last)
    crop_image_blank = crop_image[:(last_palo[1]+last_palo[3])+75,30:width-29]	
    cv2.imwrite(OUTPUT_DIR + '/' + basename, crop_image_blank)
