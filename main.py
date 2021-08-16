from api.detection.scanner.scanner import DocScanner
from test_detection import *
import cv2

image_name = 'IMG_6971'

orig_file_path = f'/home/cobaia/Desktop/frite/api/images/vetor_exames/{image_name}.jpg'
scanner = DocScanner()
scanner.scan(orig_file_path)

scanner_file_path = f'/home/cobaia/Desktop/frite/api/tests/v2/scanner/{image_name}.jpg'
crop_image_header_footer(scanner_file_path)

crop_header_footer_file_path = f'/home/cobaia/Desktop/frite/api/tests/v2/crop_header_footer/{image_name}.jpg'
image = cv2.imread(crop_header_footer_file_path)

FONT = cv2.FONT_HERSHEY_SIMPLEX
FONT_SCALE = 1
COLOR = (255, 0, 0)
THICKNESS = 2
HEIGHT = image.shape[0]
WIDTH = image.shape[1]

thresh = process_image(image)
palos_list = contour_filter(thresh, HEIGHT, WIDTH)
sort_list = sort_contours_left_to_right(palos_list)
crop_final_path = f'/home/cobaia/Desktop/frite/api/tests/v2/crop/{image_name}.jpg'
crop_image(image, image_name, sort_list, HEIGHT, WIDTH)

image = cv2.imread(crop_final_path)
HEIGHT = image.shape[0]
WIDTH = image.shape[1]
thresh = process_image(image)
palos_list = contour_filter(thresh, HEIGHT, WIDTH)
sort_list = sort_contours_left_to_right(palos_list)
row_list = set_rows(sort_list)
palographic_test = set_palographic_test(row_list, image, image_name, FONT, FONT_SCALE, COLOR, THICKNESS)

intervals_list = set_intervals(palographic_test)
size_palos = get_palos_size(intervals_list)
size_palos_interval = get_size_palos_interval(size_palos)
impulsivity = get_major_palo_test(size_palos).h - get_minor_palo_test(size_palos).h

print('impulsividade: {}'.format(impulsivity))

palos_per_interval = set_plot_yield(intervals_list)
print(palos_per_interval)

sum_diff = get_sum_diff(intervals_list)
total_palos = get_total_palos(intervals_list)

print('total de palos: {}'.format(total_palos))
print('soma das diferencas: {}'.format(sum_diff))
print('NOR: {}'.format(calculate_nor(sum_diff, total_palos)))