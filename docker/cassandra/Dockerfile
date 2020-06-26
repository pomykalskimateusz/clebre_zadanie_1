FROM cassandra

COPY ["schema.cql", "/"]

COPY ["cassandra-init.sh", "wait-for-it.sh", "/"]

RUN ["chmod", "+x", "/docker-entrypoint.sh"]
RUN ["chmod", "+x", "/wait-for-it.sh"]
RUN ["chmod", "+x", "/cassandra-init.sh"]

ENTRYPOINT [ "/cassandra-init.sh" ]

CMD ["cassandra", "-f" ]