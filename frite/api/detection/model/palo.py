class Palo:

    def __init__(self, roi, row, x, y, w, h, area, centroid_x, centroid_y, is_last_row, is_interval):
        self.roi = roi
        self.row = row
        self.x = x
        self.y = y
        self.w = w
        self.h = h
        self.area = area
        self.centroid_x = centroid_x
        self.centroid_y = centroid_y
        self.is_last_row = is_last_row
        self.is_interval = is_interval

    def get_centroid_x(self):
        return self.centroid_x

    def get_centroid_y(self):
        return self.centroid_y

    def is_major(self, palo):
        if palo.centroid_x > self.get_centroid_x():
            diff = self.get_centroid_y() - palo.centroid_y
            if (diff <= 15) and (diff >= -15):
                return True
        
        return False

    def is_minor(self, palo):
        if palo.centroid_x < self.get_centroid_x():
            diff = self.get_centroid_y() - palo.centroid_y
            if (diff <= 15) and (diff >= -15):
                return True
        return False