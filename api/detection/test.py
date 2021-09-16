# import cv2, json
# from image import process_image
# from ordination import check_palo, set_centroid_rectangle

# img = cv2.imread('/usr/src/frite/api/detection/images/crop/IMG_6971.jpg')

# thresh = process_image(img)

# cnts = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
# cnts = cnts[0] if len(cnts) == 2 else cnts[1]

# palos_list = []
# for c in cnts:
#     x,y,w,h = cv2.boundingRect(c)
#     if check_palo(x, y, w, h):
#         palos_list.append((x, y, w, h))


# obj = []
# for palo in palos_list:
#     #cv2.rectangle(img, (palo[0], palo[1]), (palo[0] + palo[2], palo[1] + palo[3]), (0, 0, 255), 1)
#     cX, cY = set_centroid_rectangle(palo[0], palo[1], (palo[0] + palo[2]), (palo[1] + palo[3]))
#     obj.append({
#         "x": palo[0],
#         "y": palo[1],
#         "width": palo[2],
#         "height": palo[3],
#         "centroidX": cX,
#         "centroidY": cY
#     })

# print(obj)
# with open('palos.json', 'w') as f:
#     f.write(json.dumps(obj))
# #cv2.imwrite('teste.jpg', img)


import requests

URL = 'http://localhost:8000/api/v1/'

def get_token():
    data = {
        "email": "admin@admin.com",
        "password": "admin"
    }
    r = requests.post(f'{URL}/login/', data=data)
    return r.json()['access_token']

def post_analysis():
    token = get_token()
    headers = {
        "Authorization": token
    }
    data = {

    }
    r = requests.post(f'{URL}/analysis/', headers=headers, data=data)

