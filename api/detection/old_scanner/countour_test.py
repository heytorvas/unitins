import numpy as np
import cv2
import matplotlib.pyplot as plt

font = cv2.FONT_HERSHEY_COMPLEX
img2 = cv2.imread('images/output.jpg', cv2.IMREAD_COLOR)
  
img = cv2.imread('images/output.jpg', cv2.IMREAD_GRAYSCALE)
  
_, threshold = cv2.threshold(img, 110, 255, cv2.THRESH_BINARY)
  
contours, _= cv2.findContours(threshold, cv2.RETR_TREE,
                               cv2.CHAIN_APPROX_SIMPLE)
  
for cnt in contours :
  
    approx = cv2.approxPolyDP(cnt, 0.009 * cv2.arcLength(cnt, True), True)
  
    cv2.drawContours(img, [approx], 0, (0, 0, 255), 5) 
    
  
    # # Used to flatted the array containing
    # # the co-ordinates of the vertices.
    # n = approx.ravel() 
    # i = 0
  
    # for j in n :
    #     if(i % 2 == 0):
    #         x = n[i]
    #         y = n[i + 1]
  
    #         # String containing the co-ordinates.
    #         string = str(x) + " " + str(y) 
  
    #         if(i == 0):
    #             # text on topmost co-ordinate.
    #             cv2.putText(img2, "Arrow tip", (x, y),
    #                             font, 0.5, (255, 0, 0)) 
    #         else:
    #             # text on remaining co-ordinates.
    #             cv2.putText(img2, string, (x, y), 
    #                       font, 0.5, (0, 255, 0)) 
    #     i = i + 1


# Showing the final image.
cv2.namedWindow('image2',cv2.WINDOW_NORMAL)
cv2.imshow('image2', img) 
  
#xiting the window if 'q' is pressed on the keyboard.
if cv2.waitKey(0) & 0xFF == ord('q'): 
    cv2.destroyAllWindows()