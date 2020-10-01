from prometheus_client import start_http_server, Counter
import time

counter = Counter('number_of_seconds', 'count seconds since start')
start_http_server(8000)

while True:
    time.sleep(1)
    counter.inc()
