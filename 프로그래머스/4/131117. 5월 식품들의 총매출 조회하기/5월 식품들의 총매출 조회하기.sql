-- 코드를 입력하세요
SELECT P.PRODUCT_ID, P.PRODUCT_NAME, P.PRICE*SUM(AMOUNT) AS TOTAL_SALES
FROM FOOD_PRODUCT P JOIN FOOD_ORDER O ON P.PRODUCT_ID = O.PRODUCT_ID
WHERE O.PRODUCE_DATE LIKE '2022-05%'
GROUP BY P.PRODUCT_NAME, P.PRODUCT_ID
ORDER BY TOTAL_SALES DESC, P.PRODUCT_ID;
