#!/bin/sh
export JWT_SECRET=$(cat "/gateway/key.pub")
echo "Starting nginx..."
/usr/local/openresty/bin/openresty -g 'daemon off;' -c /gateway/nginx.conf