# vr-autorizador-api
VR Autorizador API - Uma Solução VR Benefícios

Projeto construído como participação de um processo seletivo como Consultor Backend.

### Ferramentas e Frameworks Utilizados
<img loading="lazy" title="Git" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" width="40" height="40"/>&nbsp;&nbsp;
<img loading="lazy" title="Java" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="40" height="40"/>&nbsp;&nbsp;
<img loading="lazy" title="Spring" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="40" height="40"/>&nbsp;&nbsp;
<img loading="lazy" title="Maven" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" width="40" height="40"/>&nbsp;&nbsp;
<img loading="lazy" title="Junit" src="https://icon.icepanel.io/Technology/svg/JUnit.svg" width="40" height="40"/>&nbsp;&nbsp;
<img loading="lazy" title="Open API" src="https://icon.icepanel.io/Technology/png-shadow-512/OpenAPI.png" width="40" height="40"/>&nbsp;&nbsp;
<img loading="lazy" title="MySQL" src="https://icon.icepanel.io/Technology/svg/MySQL.svg" width="40" height="40"/>&nbsp;&nbsp;
<img loading="lazy" title="Docker" src="https://icon.icepanel.io/Technology/svg/Docker.svg" width="40" height="40"/>&nbsp;&nbsp;

### Build e Testes Unitários

Para gerar a build do projeto e executar os testes unintários, execute os seguintes comandos:

```
mvn clean package
mvn clean test
```

### Cobertura de Código

A cobertura de Código foi fechada com **93%**, conforme imagem abaixo:

![Jacoco Maven Plugin](jacoco.png?raw=true "Jacoco Maven Plugin")
**target/site/jacoco/jacoco.html**

### Executando a Aplicação

Acesse a raiz do projeto e rode o seguintes comandos:
````
docker compose build
docker compose up -d
````
### Chamando os serviços

**1 - Via Insomnia**

Na raiz do projeto consta um arquivo denominado **insomnia.json**, contendo a coleção das chamadas dos endpoints.
Baixe e instale o insomnia em sua máquina e realize a importação deste arquivo.

**2 - Via OpenAPI**

Após a execução do projeto, acesse o link **http://yourhost:8080/autorizador/v1/swagger-ui.html**
Será solicitado as credenciais de acesso (HTTP Basic), sendo estas:
 * Username: admin
 * Password: vr@123**

**OBS:** Troque o termo <i>yourhost</i> da URL pelo IP/hostame da máquina que está rodando a aplicação. 
