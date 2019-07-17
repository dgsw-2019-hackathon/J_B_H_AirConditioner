#import cv2
#import RPi.GPIO as gpio
import socket
import serial
import threading
import time
import pymysql
import datetime
##웹캠에서 영상을 읽어온다
#cap = cv2.VideoCapture(0)
#cap.set(3, 640) #WIDTH
#cap.set(4, 480) #HEIGHT

##얼굴 인식 캐스케이드 파일 읽는다
#face_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')

#while(True):
#    # frame 별로 capture 한다
#    ret, frame = cap.read()

#    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
#    faces = face_cascade.detectMultiScale(gray, 1.3, 5)

#    #인식된 얼굴 갯수를 출력
#    print(len(faces))

#    # 인식된 얼굴에 사각형을 출력한다
#    for (x,y,w,h) in faces:
#         cv2.rectangle(frame,(x,y),(x+w,y+h),(255,0,0),2)

#    #화면에 출력한다
#    cv2.imshow('frame',frame)
#    if cv2.waitKey(1) & 0xFF == ord('q'):
#        break

#cap.release()
#cv2.destroyAllWindows()

ser = serial.Serial('/dev/ttyACM0', 9600)
sensor = Adafruit_DHT.DHT11
conn = pymysql.connect(host="localhost", user="DGSW1", password="12345678",
                       db="mysql", charset="utf8")
curs = conn.cursor()

sql1 = "SELECT tempe FROM fan ORDER BY pk DESC limit 1" #온도
sql2 = "SELECT hum FROM fan ORDER BY pk DESC limit 1"
sql3 = "SELECT starttime FROM fan ORDER BY pk DESC limit 1"
sql4 = "SELECT finishtime FROM fan ORDER BY pk DESC limit 1"

gpio.setmode(gpio.BCM)
Moter = 4
DHTpin = 3
Serinput = ""
temperwhile = 0
timerwhile = 0
gpio.setup(Moter, gpio.OUT)
def control1():#temperture compare and control
    global curs
    global sql1
    global temperwhile
    data_temp = ""
    serData_temp = ""
    while temperwhile:
        curs.execute(sql1)
        data_temp = curs.fetchall()
        data_temp = int(data_temp)
        if ser.readable():
            res = ser.readline()
            res = res.decode()
            list = res.split("/")
            serData_temp = float(list[0])
            serData_temp = int(serData_temp)
        if serData_temp > data_temp:
            ser.write([1])
        elif serData_temp == data_temp:
            ser.write([0])
        elif serData_temp == None:
            print("not connect")
        else:
            ser.write([0])

def control2():#timer function
    global sql3
    global sql4
    global curs
    global timerwhile
    data_start=""
    data_finish=""
    while timerwhile:
        curs.execute(sql3)
        data_start = curs.fetchall()
        now = datetime.now()
        hm = now.hour+"/"+now.minute
        
try:
    server = threading.Thread(target=server)
    server.daemon = True
    server.start()
except Exception as e:
    print(e)