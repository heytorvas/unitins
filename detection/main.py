from scanner.scanner import DocScanner
from image import resize_image, crop_image_header_footer, crop_image
from ordination import set_ordination
from analytics import *

image_name = 'IMG_6971'

def main():
    ########### SCANNER ###########
    orig_file_path = f'detection/images/original/{image_name}.jpg'
    scanner = DocScanner()
    scanner.scan(orig_file_path)

    ########### RESIZE ###########
    img_scan_path = f'detection/images/scanner/{image_name}.jpg'
    resize_image(img_scan_path)

    ########### CROP HEADER AND FOOTER ###########
    img_resize_path = f'/home/cobaia/Desktop/frite/detection/images/resize/{image_name}.jpg'
    crop_image_header_footer(img_resize_path)

    ########### CROP IMAGE ###########
    img_crop_header_footer_path = f'/home/cobaia/Desktop/frite/detection/images/crop_header_footer/{image_name}.jpg'
    crop_image(img_crop_header_footer_path)

    ########### ORDINATION ###########
    img_crop = f"/home/cobaia/Desktop/frite/detection/images/crop/{image_name}.jpg"
    palographic_test, rows_list = set_ordination(img_crop)

    ########### ANALYTICS ###########
    total_palos = len(palographic_test)-5
    print('produtividade: ', total_palos)

    intervals_list = set_intervals(palographic_test)
    get_distance_between_palos(intervals_list)


    palos_by_interval = set_plot_yield(intervals_list)
    print('palos por intervalo: ', palos_by_interval)

    sum_diff = get_sum_diff(intervals_list)
    print('soma das diferenças: ', sum_diff)

    nor = calculate_nor(sum_diff, total_palos)
    print('NOR: ', nor)

    size_palos = get_palos_size(intervals_list)
    impulsivity = get_major_palo_test(size_palos).h - get_minor_palo_test(size_palos).h
    print('impulsividade: {}'.format(impulsivity))

    print('tamanho dos palos: {}'.format(calculate_size_palos(size_palos)))

main()
