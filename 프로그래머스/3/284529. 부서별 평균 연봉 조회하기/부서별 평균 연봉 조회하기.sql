-- 코드를 작성해주세요
SELECT D.DEPT_ID, D.DEPT_NAME_EN, ROUND(AVG(SAL)) AS AVG_SAL
FROM HR_DEPARTMENT D, HR_EMPLOYEES E
WHERE D.DEPT_ID = E.DEPT_ID
GROUP BY DEPT_ID
ORDER BY AVG_SAL DESC;