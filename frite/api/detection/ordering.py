import cv2, os, json
from detection.image import process_image
from detection.util import calculate_area, set_centroid_rectangle
from detection.model.palo import Palo

BASE_LIMIT = 120
WIDTH_LIMIT = 200
HEIGHT_LIMIT = 140

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

def get_palos_image(img_path):
    img = cv2.imread(img_path, 0)
    converted_img = cv2.cvtColor(img, cv2.COLOR_GRAY2BGR)
    thresh = process_image(converted_img)
    cnts = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    cnts = cnts[0] if len(cnts) == 2 else cnts[1]

    palos_list = []
    for c in cnts:
        x,y,w,h = cv2.boundingRect(c)
        if check_palo(x, y, w, h):
            if calculate_area(w, h) > 180:
                palos_list.append((x, y, w, h))

    obj = []
    for palo in palos_list:
        cX, cY = set_centroid_rectangle(palo[0], palo[1], (palo[0] + palo[2]), (palo[1] + palo[3]))
        obj.append({
            "x": palo[0],
            "y": palo[1],
            "width": palo[2],
            "height": palo[3],
            "centroidX": cX,
            "centroidY": cY
        })
    
    return obj

def read_palos_list(obj):
    palo_list = []
    for palo in obj:
        p = Palo(None, None, palo['x'], palo['y'], palo['width'], palo['height'], 
                calculate_area(palo['width'], palo['height']), palo['centroidX'], palo['centroidY'], None, None)
        palo_list.append(p)
    return palo_list

def search_near_palo(palo_actual, palo_list):
    result = None
    x_axis = 9999999999

    for palo in palo_list:
        if palo_actual.is_major(palo):
            if palo.centroid_x < x_axis:
                result = palo
                x_axis = result.centroid_x

    return result

def search_previous_palo(palo_actual, palo_list):
    result = None
    x_axis = 0

    for palo in palo_list:
        if palo_actual.is_minor(palo):
            if palo.centroid_x > x_axis:
                result = palo
                x_axis = result.centroid_x

    return result

def return_first_row(first_palo, palo_list):
    result = []
    result.append(first_palo)

    next_palo = search_near_palo(first_palo, palo_list)
    while next_palo != None:
        result.append(next_palo)
        next_palo = search_near_palo(next_palo, palo_list)
    
    return result

def first_palo(palo_list):
    palo_list.sort(key=lambda x: x.centroid_y)
    
    previous_palo = None
    if len(palo_list) > 0:
        previous_palo = search_previous_palo(palo_list[0], palo_list)
        if previous_palo == None:
            previous_palo = palo_list[0]

    result = None
    while previous_palo != None:
        result = previous_palo
        previous_palo = search_previous_palo(previous_palo, palo_list)
    
    return result

def return_palos(palo_list):
    result = []

    number = 0
    row = 0

    clone = palo_list

    first = first_palo(clone)
    while first != None:
        row_list = return_first_row(first, clone)
        row += 1

        for palo in row_list:
            number = number + 1
            palo.roi = number
            palo.row = row
            result.append(palo)
            clone.remove(palo)

        first = first_palo(clone)
    
    return result

def set_ordination(img_path):
    OUTPUT_DIR = '/usr/src/frite/api/detection/images/sof'
    basename = os.path.basename(img_path)
    img = cv2.imread(img_path, 0)

    obj = get_palos_image(img_path)
    palo_list = read_palos_list(obj)
    result = return_palos(palo_list)

    for palo in result:
        cv2.rectangle(img, (palo.x, palo.y), (palo.x + palo.w, palo.y + palo.h), (0, 0, 255), 1)
        cv2.putText(img, str(palo.roi), (palo.x,palo.y), cv2.FONT_HERSHEY_COMPLEX_SMALL, 0.75, (0,255,0), 1)

    row_values = {palo.row for palo in result}
    for row in row_values:
        aux = [x for x in result if x.row == int(row)]
        for i in aux:
            if aux[len(aux)-1] == i:
                i.is_last_row = True
            else:
                i.is_last_row = False

    aux = []
    for i in result:
        aux.append(i.__dict__)
        
    with open(f'/usr/src/frite/api/detection/images/json/{basename}.json', 'w') as f:
        f.write(json.dumps(aux))

    cv2.imwrite(OUTPUT_DIR + '/' + basename, img)
    return result, None