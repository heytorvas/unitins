import cv2

def createClare(channelB, channelG, channelR):

    # create a CLAHE object(Contrast Limited Adaptive Histogram Equalization)

    clahe = cv2.createCLAHE(clipLimit=2.0, tileGridSize=(8, 8))
    cl1 = clahe.apply(channelB)
    cv2.imwrite('bluechannel.jpg', cl1)

    clahe1 = cv2.createCLAHE(clipLimit=2.0, tileGridSize=(8, 8))
    cl2 = clahe.apply(channelG)
    cv2.imwrite('greenchanel.jpg', cl2)

    clahe2 = cv2.createCLAHE(clipLimit=2.0, tileGridSize=(8, 8))
    cl3 = clahe.apply(channelR)
    cv2.imwrite('redChannel.jpg', cl3)

    result = cv2.merge((cl1, cl2, cl3))


    cv2.imwrite("image_after_equalization.jpg", result)

    return result

def medianFilter(image):
    median = cv2.medianBlur(image, 5, None)

    b = median[:, :, 0]
    g = median[:, :, 1]
    r = median[:, :, 2]

    cv2.imwrite("images/tmp/median_result.jpg", median)
    return b,g,r

img = cv2.imread('images/tmp/scanner_output.jpg')
b,g,r = medianFilter(img)

#afterEqualization = createClare(b,g,r)
#showHistogram(afterEqualization)