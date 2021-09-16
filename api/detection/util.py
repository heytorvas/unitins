from math import sqrt

def calculate_area(w, h):
	return w * h

def calculate_distance(x1,y1,x2,y2):
    return sqrt((x2 - x1)**2 + (y2 - y1)**2)

def calculate_mm(pixels):
	DPI = 96
	return (pixels * 25.4) / DPI

def calculate_nor(sum_diff, total_palos):
	return (sum_diff * 100) / total_palos

def check_border_xy(x, y):
    if x > 15 and y > 10:
        return True
    else:
        return False