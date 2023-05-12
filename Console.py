category = {'벚꽃' : {'기숙사 가는 길' : ["리뷰1", "리뷰2"]},
             '배달음식' : {'막걸리 동산' : ["리뷰1", "리뷰2"],
                        '도서관 휴게실' : ["리뷰1", "리뷰2", "리뷰3"]}, '흡연' : [], '피크닉' : []}

for key in category.keys():
    print(key)

keyword = input('어떤 장소를 찾으세요? : ')

for place in category[keyword].keys():
    print(place)
    for review in category[keyword][place]:
        print(review)