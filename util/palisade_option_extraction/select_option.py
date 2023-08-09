import requests
import mysql.connector
from collections import defaultdict

sub_option_dictionary = defaultdict()
select_option_set = defaultdict()
h_genuine_set = defaultdict()
select_option_dictionary = defaultdict()
model_select_mapping_dictionary = defaultdict(list)
select_sub_mapping_dictionary = defaultdict(list)

saleModelCodes = ["LXJJ7DST5", "LXJJ7DAT5", "LXJJ7DBT5", "LXJJ7DCT5",
                  "LXJJ7MST5", "LXJJ7MAT5", "LXJJ7MBT5", "LXJJ7MCT5",
                  "LXJJ8DST5", "LXJJ8DAT5", "LXJJ8DBT5", "LXJJ8DCT5",
                  "LXJJ8MST5", "LXJJ7MAT5", "LXJJ7MBT5", "LXJJ7MCT5",
                  "LXJJ7DSA5", "LXJJ7DAA5", "LXJJ7DBA5", "LXJJ7DCA5",
                  "LXJJ8DSA5", "LXJJ8DAA5", "LXJJ8DBA5", "LXJJ8DCA5",
                  "LXJJ8MSA5", "LXJJ8MAA5", "LXJJ8MBA5", "LXJJ8MCA5",
                  "LXJJ7MSA5", "LXJJ7MAA5", "LXJJ7MBA5", "LXJJ7MCA5"]

def find_select_option(saleModelCode):
    response = requests.get("https://www.hyundai.com/kr/ko/gw/product/v1/product/options/" + saleModelCode
                            + "?saleModelCode=" + saleModelCode
                            + "&carAbbreviation=LX")
    data = response.json()['data']
    select_options = data["optionChoice"]
    h_genuine = data["optionTuix"]

    #선택옵션 목록을 저장하고 모델과 선택옵션의 매핑 정보를 저장한다
    for select_option in select_options:
        select_option_id = select_option["choiceOptionCode"]
        model_select_mapping_dictionary[saleModelCode].append(select_option_id)
        select_option_set[select_option_id] = saleModelCode

    for h_genuine_option in h_genuine:
        h_genuine_option_id = h_genuine_option["tuixOptionCode"]
        model_select_mapping_dictionary[saleModelCode].append(h_genuine_option_id)
        h_genuine_set[h_genuine_option_id] = saleModelCode

def extract_sub_option(sub_option_list, select_option_id):
    for sub_option in sub_option_list:
        option_datas = {}
        option_datas["id"] = sub_option["carSpcCd"]
        option_datas["name"] = sub_option["pcltSpcNm"]
        option_datas["description"] = sub_option["spcExplSbl"]
        option_datas["imgUrl"] = "https://www.hyundai.com" + sub_option["carOptionDetailPcImg"][0]["imageFilePath"]
        sub_option_dictionary[sub_option["carSpcCd"]] = option_datas
        select_sub_mapping_dictionary[select_option_id].append(sub_option["carSpcCd"])
    
def extract_select_option(option_set, type):
    for select_option_id in option_set.keys():
        saleModelCode = option_set[select_option_id]
        response = requests.get("https://www.hyundai.com/kr/ko/gw/product/v1/product/option/"+ saleModelCode
                                + "?saleModelCode=" + saleModelCode
                                + "&carOptionCode=" + select_option_id
                                + "&carOptionTypeCode=" + type)
        
        data = response.json()["data"]
        select_option_data = data["carOptionDetailchoice"]

        #선택 옵션 세부정보 확인
        option_datas = {}
        option_datas["id"] = select_option_data["optCtyNo"]
        option_datas["name"] = select_option_data["pcltSpcNm"]
        option_datas["description"] = select_option_data["spcExplSbl"]
        if type=="O":
            option_datas["category"] = "선택옵션"
        elif "[N퍼포먼스파츠]" in option_datas["name"]:
            option_datas["category"] = "N Performance"
        else:
            option_datas["category"] = "H Geniune Accessories"
        option_datas["price"] = select_option_data["saleCtyPce"]
        option_datas["isContainSub"] = False
        
        if data["carOptionPackageDetail"]:
            option_datas["isContainSub"] = True
            option_datas["imgUrl"] = "https://www.hyundai.com" + data["carOptionPackageDetail"][0]["carOptionDetailPcImg"][0]["imageFilePath"]
            option_datas["subOptionList"] = extract_sub_option(data["carOptionPackageDetail"],select_option_data["optCtyNo"])
        else:
            option_datas["imgUrl"] = "https://www.hyundai.com" + select_option_data["carOptionDetailPcImg"][0]["imageFilePath"]


        select_option_dictionary[select_option_id] = option_datas


for saleModelCode in saleModelCodes:
    find_select_option(saleModelCode)
extract_select_option(select_option_set,"O")
extract_select_option(h_genuine_set,"T")

print(len(select_option_dictionary))
print(select_option_dictionary)
print(len(sub_option_dictionary))
print(sub_option_dictionary)
print(len(select_sub_mapping_dictionary))
print(select_sub_mapping_dictionary)
print(len(model_select_mapping_dictionary))
print(model_select_mapping_dictionary)

print("--------------------------------")
print("--------------------------------")
for select_option_id in model_select_mapping_dictionary["LXJJ8DAA5"]:
    select_option=select_option_dictionary[select_option_id]
    print("선택옵션명: %s, 카테코리: %s" % (select_option["name"],select_option["category"]))
    
    for sub_option_id in select_sub_mapping_dictionary[select_option_id]:
        print("--하위옵션명: %s" % (sub_option_dictionary[sub_option_id]["name"]))