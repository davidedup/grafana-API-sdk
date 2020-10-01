from prometheus_client import start_http_server, Summary
from random import random
from time import sleep

summary = Summary('request_processing_seconds', 'time spent processing request')

@summary.time()
def process_request(t):
    sleep(t)

start_http_server(7999)
while True:
    process_request(random())
