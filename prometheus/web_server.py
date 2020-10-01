from http import server
from prometheus_client import MetricsHandler, Counter, Gauge, Histogram
from sys import argv
from random import randint

PORT = 8001
if len(argv) > 1:
    PORT = int(argv[1])

counter = Counter('http_requests', 'number of requests served')
gauge = Gauge('queue', 'simulation of a queue')
histogram = Histogram('request_duration', 'duration of a request')

class Handler(MetricsHandler):
    def do_GET(self):
        if self.path == '/metrics':
            super().do_GET()
        elif self.path == '/':
            counter.inc()
            rand = randint(1, 10)
            histogram.observe(rand)
            message = str(rand).encode()
            self.send_response(200)
            self.send_header('Content-Length', len(message))
            self.end_headers()
            self.wfile.write(message)
        elif self.path == '/enqueue':
            counter.inc()
            gauge.inc()
            message = b'enqueued\n'
            self.send_response(200)
            self.send_header('Content-Length', len(message))
            self.end_headers()
            self.wfile.write(message)
        elif self.path == '/dequeue':
            counter.inc()
            gauge.dec()
            message = b'dequeued\n'
            self.send_response(200)
            self.send_header('Content-Length', len(message))
            self.end_headers()
            self.wfile.write(message)

httpd = server.HTTPServer(('', PORT), Handler)
httpd.serve_forever()

