#!/bin/sh
echo "Starting nginx..."
/usr/local/openresty/bin/openresty -g 'daemon off;' -c /gateway/nginx.conf