from detection.scanner.scanner import DocScanner
from detection.image import resize_image, crop_image_header_footer, crop_image
#from detection.ordination import set_ordination
from detection.ordering import set_ordination
from detection.analytics import *
from detection.util import calculate_nor


def main(path):
    
    ########### SCANNER ###########
    #orig_file_path = f'detection/images/original/{image_name}.jpg'
    orig_file_path = f'media/{path}'
    scanner = DocScanner()
    scanner.scan(orig_file_path)

    image_name = str(path).split('/')[1].strip()

    ########### RESIZE ###########
    img_scan_path = f'/usr/src/frite/api/detection/images/scanner/{image_name}'
    resize_image(img_scan_path)

    ########### CROP HEADER AND FOOTER ###########
    img_resize_path = f'/usr/src/frite/api/detection/images/resize/{image_name}'
    crop_image_header_footer(img_resize_path)

    ########### CROP IMAGE ###########
    img_crop_header_footer_path = f'/usr/src/frite/api/detection/images/crop_header_footer/{image_name}'
    crop_image(img_crop_header_footer_path)

    ########### ORDINATION ###########
    img_crop = f"/usr/src/frite/api/detection/images/crop/{image_name}"
    palographic_test, rows_list = set_ordination(img_crop)

    ########### ANALYTICS ###########
    total_palos = len(palographic_test)-5
    print('\nPRODUTIVIDADE: ', total_palos, '\n')

    intervals_list = set_intervals(palographic_test)
    print(intervals_list)
    dist_palo = get_distance_between_palos(intervals_list)
    print('E/5: ', dist_palo, '\n')

    palos_by_interval = set_plot_yield(intervals_list)
    print('PALOS POR INTERVALO: ', palos_by_interval, '\n')

    sum_diff = get_sum_diff(intervals_list)
    print('SOMA DAS DIFERENÇAS: ', sum_diff)
    nor = calculate_nor(sum_diff, total_palos)
    print('NOR: ', nor, '\n')

    size_palos = get_palos_size(intervals_list)
    impulsiveness = get_major_palo_test(size_palos).h - get_minor_palo_test(size_palos).h
    print('IMPULSIVIDADE: ', impulsiveness, '\n')

    print('TAMANHO DOS PALOS: ', calculate_size_palos(size_palos), '\n')

    return {
        "productivity": total_palos,
        "nor": nor,
        "income": str(palos_by_interval),
        "distance_between_palos": dist_palo,
        "palos_size": calculate_size_palos(size_palos),
        "impulsiveness": impulsiveness,
        "difference_sum": sum_diff
    }
