WITH initial_sales AS 
(
	SELECT cust, prod, month, sum(quant) AS MONTH_SALES
	FROM sales
	GROUP BY cust, prod, month
	ORDER BY cust, prod, month

), final_sales AS
(
	SELECT t1.cust, t1.prod, t1.month, sum(t2.MONTH_SALES) AS final_sales
	FROM initial_sales t1, initial_sales t2
	WHERE t1.cust = t2.cust AND t1.prod = t2.prod AND t1.month >= t2.month
	GROUP BY t1.cust, t1.prod, t1.month
	ORDER BY t1.cust, t1.prod, t1.month

), target_sales AS
(
	SELECT cust, prod, round(sum(MONTH_SALES)/3,0) AS TARGETED_SALES
	FROM initial_sales
	GROUP BY cust, prod
	ORDER BY cust, prod
)

SELECT t1.cust, t1.prod, min(t1.month)
FROM final_sales t1, target_sales t2
WHERE t1.cust = t2.cust AND t1.prod = t2.prod AND final_sales >= targeted_sales
GROUP BY t1.cust, t1.prod
ORDER BY t1.cust, t1.prod