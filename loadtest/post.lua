# post.lua
-- example HTTP POST script which demonstrates setting the
-- HTTP method, body, and adding a header

wrk.method = "POST"
wrk.body = '{"items":[{"productId":"5678","qty":2,"price":200}]}'
wrk.headers["Content-Type"] = "application/json"

