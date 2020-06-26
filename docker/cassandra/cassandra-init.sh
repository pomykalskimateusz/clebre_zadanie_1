#!/bin/bash
set -e

if [ ! -f cassandra_setuped ]; then
  echo '=> Starting Cassandra'
  /docker-entrypoint.sh "$@" &
  cassandra_pid="$!"

  echo '=> Waiting for Cassandra to become available'
  /wait-for-it.sh -t 240 127.0.0.1:9042
  echo '=> Cassandra is available'

  cqlsh -f /schema.cql 127.0.0.1 9042

  kill -s TERM "$cassandra_pid"
  set +e
  wait "$cassandra_pid"
  if [ $? -ne 143 ]; then
    echo >&2 'Cassandra setup failed'
    exit 1
  fi
  set -e

  touch cassandra_setuped
  echo 'Cassandra has been setup, starting normally'
fi

exec /docker-entrypoint.sh "$@"
