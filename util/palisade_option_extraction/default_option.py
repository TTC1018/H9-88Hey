import requests
from collections import defaultdict

option_dictionary = defaultdict()

response = requests.get("https://www.hyundai.com/kr/ko/gw/product/v1/product/options/LXJJ7DCT5?saleModelCode=LXJJ7DCT5&carAbbreviation=LX")
data = response.json()['data']

option_basic = data["optionBasic"]
for basic_category in option_basic:
    category_code = basic_category['basicOptionCategoryCode']
    category_name = basic_category['basicOptionCategoryName']
    car_options = basic_category['carOptions']

    option_dictionary[category_code] = []

    option_dictionary[category_code].append(category_name)
    option_dictionary[category_code].append(car_options)

for key in option_dictionary.keys():
    print(option_dictionary[key])