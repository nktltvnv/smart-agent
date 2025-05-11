FROM supercorp/supergateway

RUN apk add --no-cache python3 py3-pip

RUN python3 -m venv /venv
ENV PATH="/venv/bin:$PATH"

RUN pip install mcp_server_time
