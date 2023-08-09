import requests
response = requests.get("https://www.hyundai.com/kr/ko/gw/product/v1/product/option/LXJJ7DCT5?saleModelCode=LXJJ7DCT5&carOptionCode=DUP&carOptionTypeCode=O")
print(response.json()["data"]["carOptionPackageDetail"] is not None)