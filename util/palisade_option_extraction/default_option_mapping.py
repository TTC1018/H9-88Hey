import requests
import mysql.connector
from collections import defaultdict

saleModelCodes = ["LXJJ7DST5", "LXJJ7DAT5", "LXJJ7DBT5", "LXJJ7DCT5",
                  "LXJJ7MST5", "LXJJ7MAT5", "LXJJ7MBT5", "LXJJ7MCT5",
                  "LXJJ8DST5", "LXJJ8DAT5", "LXJJ8DBT5", "LXJJ8DCT5",
                  "LXJJ8MST5", "LXJJ8MAT5", "LXJJ8MBT5", "LXJJ8MCT5",
                  "LXJJ7DSA5", "LXJJ7DAA5", "LXJJ7DBA5", "LXJJ7DCA5",
                  "LXJJ8DSA5", "LXJJ8DAA5", "LXJJ8DBA5", "LXJJ8DCA5",
                  "LXJJ8MSA5", "LXJJ8MAA5", "LXJJ8MBA5", "LXJJ8MCA5",
                  "LXJJ7MSA5", "LXJJ7MAA5", "LXJJ7MBA5", "LXJJ7MCA5"]


mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  database="mycar_test"
)
mycursor = mydb.cursor()

def paramify(value):
  return "\'"+str(value)+"\'"

def mapping_default_option_with_model(saleModelCode):
    response = requests.get("https://www.hyundai.com/kr/ko/gw/product/v1/product/options/"+saleModelCode+"?saleModelCode="+saleModelCode+"&carAbbreviation=LX")
    data = response.json()['data']

    option_basic = data["optionBasic"]
    for basic_category in option_basic:
        car_options = basic_category['carOptions']


        #옵션 삽입
        for option in car_options:
            option_id = option['basicOptionCode']
            mycursor.execute("INSERT INTO defaultOption_carTrims (car_normal_types_id,default_option_id) VALUES (%s,%s)"
                       %(paramify(saleModelCode),paramify(option_id)))
            mydb.commit()

for saleModelCode in saleModelCodes:
    mapping_default_option_with_model(saleModelCode)
