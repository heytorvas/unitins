import cv2, os
import numpy as np
from model.palo import Palo
from util import calculate_distance, calculate_area

BASE_LIMIT = 120
WIDTH_LIMIT = 200
HEIGHT_LIMIT = 140

# sort rowboxes on y coordinate
def take_second(elem):
    return elem[1]

def check_palo(x, y, w, h):
    base = calculate_area(w, h)
    if w > h: #intervalo = largura maior que altura
        if base > 80:
            if w < WIDTH_LIMIT:
                return True
            else:
                return False
        else:
            return False
    elif h > w: #palo = altura maior que largura
        if base > BASE_LIMIT:
            if h > HEIGHT_LIMIT:
                return False
            else:
                return True
        else:
            return False

def get_box_line(img):
    label,imgth=cv2.threshold(img,80,255,cv2.THRESH_BINARY_INV)
    erod = cv2.erode(imgth, np.ones((11, 1)))
    dila = cv2.dilate(erod, np.ones((1, 30)))
    erod = cv2.erode(dila, np.ones((20, 1)))
    dila = cv2.dilate(erod, np.ones((1, 120)))
    #cv2.imshow("h", dila)
    rowcontours = cv2.findContours(dila, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    rowcontours = rowcontours[0] if len(rowcontours) == 2 else rowcontours[1]
    box_lines=[]
    for con in rowcontours:
        xmin, ymin, w, h = cv2.boundingRect(con)
        xmax=xmin+w
        ymax=ymin+h
        box_lines.append([xmin,ymin,w,h])
        cv2.rectangle(img, (xmin,ymin), (xmax,ymax), 100, 5)
    return box_lines

def take_first(elem):
    return elem[0]

def get_box_palito_line(row):
    bboxes=[]
    # find contours of each character in the row
    label, imgth = cv2.threshold(row, 80, 255, cv2.THRESH_BINARY_INV)
    filter = cv2.morphologyEx(imgth, cv2.MORPH_CLOSE, np.ones((15, 1)))
    contours = cv2.findContours(filter, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    contours = contours[0] if len(contours) == 2 else contours[1]

    for cntr in contours:
        x, y, w, h = cv2.boundingRect(cntr)
        bboxes.append((x ,y, w, h))

    bboxes.sort(key=take_first)
    return bboxes

def set_ordination(img_path):
    OUTPUT_DIR = '/home/cobaia/Desktop/frite/detection/images/sof'
    basename = os.path.basename(img_path)
        
    img = cv2.imread(img_path,0)
    row_boxes=get_box_line(img)
    row_boxes.sort(key=take_second)

    index = 1

    palographic_test = []
    rows_list = []

    # loop over each row
    for xr,yr,wr,hr in row_boxes:
        # crop the image for a given row
        row = img[yr:yr+hr, xr:xr+wr]

        row_list = []
        bboxes = get_box_palito_line(row)
        # draw sorted boxes
        for box in bboxes:
            #x,y,w,h = cv2.boundingRect(box)
            xb,yb,wb,hb = box
            xb+=xr
            yb+=yr
            
            if check_palo(xb, yb, wb, hb):
                cv2.rectangle(img, (xb, yb), (xb + wb, yb + hb), (0, 0, 255), 1)
                # M = cv2.moments(box)
                # cX = int(M["m10"] / M["m00"])
                # cY = int(M["m01"] / M["m00"])

                cX, cY = set_centroid_rectangle(xb, yb, (xb + wb), (yb + hb))
                
                p = Palo(index, xb, yb, wb, hb, calculate_area(wb, hb), cX, cY, xb + wb, yb + hb, calculate_distance(0,0, cX, cY), calculate_distance(cX, cY, 0, cY), calculate_distance(cX, cY, img.shape[1], cY), None, None)
                if bboxes[len(bboxes)-1] == box:
                    p.is_last_row = True
                else:
                    p.is_last_row = False
                    
                palographic_test.append(p)
                row_list.append(p)

                cv2.putText(img, str(index), (xb,yb), cv2.FONT_HERSHEY_COMPLEX_SMALL, 0.75, (0,255,0), 1)
                index = index + 1
        rows_list.append(row_list)

    cv2.imwrite(OUTPUT_DIR + '/' + basename, img)
    return palographic_test, rows_list


def set_centroid_rectangle(x1, y1, x2, y2):
    return int((x1+x2)/2), int((y1+y2)/2)