class Palo:

    def __init__(self, roi, x, y, w, h, area, cX, cY, baseX, baseY, distance, margin_left, margin_right, is_last_row, is_interval):
        self.roi = roi
        self.x = x
        self.y = y
        self.w = w
        self.h = h
        self.area = area
        self.cX = cX
        self.cY = cY
        self.baseX = baseX
        self.baseY = baseY
        self.distance = distance
        self.margin_left = margin_left
        self.margin_right = margin_right
        self.is_last_row = is_last_row
        self.is_interval = is_interval
