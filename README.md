- 项目名称：gemini-uaa
- 项目描述：认证授权服务

```shell
### 生成密钥
#keytool -genkey -alias gemini-uaa -keyalg RSA -keystore gemini-uaa.jks -storepass 123456 -keypass 123456 -validity 36500 -dname "CN=gemini-uaa,OU=gemini,O=glseven,L=GZ,ST=GD,C=CN"
#
### 导出公钥
#keytool -list -rfc --keystore gemini-uaa.jks | openssl x509 -inform pem -pubkey -noout > gemini-uaa.pub

## 生成jwt密钥
openssl genrsa -out gemini-uaa.pem 2048

## 生成公钥
openssl rsa -in gemini-uaa.pem -pubout -out gemini-uaa.pub
```

```http request
### 获取token
POST /token HTTP/1.1
Host: localhost:7000
Authorization: Basic dXNlcjpwYXNzd29yZA==

### 验证token
POST /demo/hello HTTP/1.1
Host: localhost:7000
Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL3VhYS5nZW1pbmkuZ2xzLmNvbSIsInN1YiI6InVzZXIiLCJleHAiOjE3MDg1MzYxNTcsImlhdCI6MTcwODUzMjU1Nywic2NvcGUiOiJST0xFX1VTRVIifQ.jo-85vHQT2ikmdFQWHJBv7IZJs5hYWTdjDFF9PSRRiE045ClXbPHNk9Y4M8U6XNyC2y-F-hRCPzbjixEN9yLKKBq4naPp3SnaglK8Rq_uoLydjdCtqMZ_DB9MKlvAXIk9CjLrDPGdA2hhES3TktJu4W_RfE19lxcn-LJf8v6B2JJ1F1nG989bN6RnizwYbd9wdl_pcSRdGkyNnJMRRBUmUo1jlC30UP4dB7YjfgkaKajW7-n26u1PNVDyqKix7rtGpIXoRM8IOTONlnxFNXInWK6SRK1o0tULaZKIgjfCsaBQUJwm7OQMHkHdtmISHNjWNKR-NDPPyM9BrVmJ7tKcQ

```