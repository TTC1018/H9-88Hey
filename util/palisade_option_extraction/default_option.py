import requests
import mysql.connector
from collections import defaultdict

option_dictionary = defaultdict()
saleModelCodes = ["LXJJ7DST5", "LXJJ7DAT5", "LXJJ7DBT5", "LXJJ7DCT5",
                  "LXJJ7MST5", "LXJJ7MAT5", "LXJJ7MBT5", "LXJJ7MCT5",
                  "LXJJ8DST5", "LXJJ8DAT5", "LXJJ8DBT5", "LXJJ8DCT5",
                  "LXJJ8MST5", "LXJJ7MAT5", "LXJJ7MBT5", "LXJJ7MCT5",
                  "LXJJ7DSA5", "LXJJ7DAA5", "LXJJ7DBA5", "LXJJ7DCA5",
                  "LXJJ8DSA5", "LXJJ8DAA5", "LXJJ8DBA5", "LXJJ8DCA5",
                  "LXJJ8MSA5", "LXJJ8MAA5", "LXJJ8MBA5", "LXJJ8MCA5",
                  "LXJJ7MSA5", "LXJJ7MAA5", "LXJJ7MBA5", "LXJJ7MCA5"]

def init_option_dict(category_code, category_name):
    if category_code not in option_dictionary:
            option_dictionary[category_code] = []
            option_dictionary[category_code].append(category_name)
            option_dictionary[category_code].append(defaultdict())

#db연결
mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  database="mycar_test"
)
mycursor = mydb.cursor()


def extract_default_option(saleModelCode):
    response = requests.get("https://www.hyundai.com/kr/ko/gw/product/v1/product/options/"+saleModelCode+"?saleModelCode="+saleModelCode+"&carAbbreviation=LX")
    data = response.json()['data']

    option_basic = data["optionBasic"]
    for basic_category in option_basic:
        category_code = basic_category['basicOptionCategoryCode']
        category_name = basic_category['basicOptionCategoryName']
        car_options = basic_category['carOptions']

        init_option_dict(category_code, category_name)

        #옵션 삽입
        for option in car_options:
            option_id = option['basicOptionCode']
            if option_id not in option_dictionary:
                default_option={}
                default_option["modelId"] = saleModelCode
                default_option["optionId"] = option["basicOptionCode"]
                default_option["optionName"] = option["basicOptionName"]
                default_option["imgUrl"] = "https://www.hyundai.com/" + option["filePath"] + option["imgFile"]
                option_dictionary[category_code][1][option_id] = default_option

#32개 모델에 대한 옵션 추출
for saleModelCode in saleModelCodes:
    extract_default_option(saleModelCode)


def paramify(value):
  return "\'"+str(value)+"\'"

#옵셥 출력
for key in option_dictionary.keys():
    option_keys = option_dictionary[key][1].keys()
    print(option_dictionary[key][0]+"("+str(len(option_keys))+")")
    for option_key in option_keys:
        default_option = option_dictionary[key][1][option_key]
        response = requests.get("https://www.hyundai.com/kr/ko/gw/product/v1/product/option/" + default_option["modelId"]
                                + "?saleModelCode=" + default_option["modelId"]
                                + "&carOptionCode="+ default_option["optionId"]
                                + "&carOptionTypeCode=B")

        default_option["description"] = response.json()["data"]["optionDetailBasic"]["spcExplSbl"]
        mycursor.execute("INSERT INTO default_option (id,name,description,image_url,category) VALUES (%s,%s,%s,%s,%s)"
                       %(paramify(default_option["optionId"]),paramify(default_option["optionName"].replace("'","''")),paramify(default_option['description'].replace("'","''")),paramify(default_option["imgUrl"]),paramify(option_dictionary[key][0])))
        mydb.commit()
        print(default_option)
