select t.prod, t.cust, t.month, 
 (select round(avg(quant)) from sales where month=t.month -1 and t.prod = prod and t.cust = cust group by prod, cust) before_avg,
 (select round(avg(quant)) from sales where month=t.month +1 and t.prod = prod and t.cust = cust group by prod, cust) after_avg
from
(select prod, cust, generate_series(1, 12) as month
from sales s
group by prod, cust) t