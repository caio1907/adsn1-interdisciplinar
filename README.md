# Programação Orientada à Objetos
### Projeto Unibra interdisciplinar
---
### Instalando o projeto
- Acessar o workspace do Eclipe (Ao iniciar o programa ele informa o caminho)
- Clonar o projeto ```git clone git@github.com:caio1907/adsn1-interdisciplinar.git```
- Abrir o projeto no Eclipe
- Instalar a biblioteca JDBC. [Baixe a última versão aqui](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc)

### Instalando o Java Swing
- Cliquei em Help, depois em Eclipse Marketplace
- Pesquise por WindowBuilder
- Clique em Install e siga as instruções da IDE

### Criando uma tela nova
- Botão direito do mouse no pacote 'com.adsn1.screens' depois em New -> Other... (Ctrl + N);
- Pesquise por JInternalFrame, selecione a opção filtrada e clique em Finish
- Dê um nome para tela e clique em Finish.

### Subindo a tela nova ou alterações no código
- Crie uma branch ```git checkout -b feature/(NovaTela)``` **sem parênteses**
- Adicione o(s) arquivo(s) novo(s) ```git add src/*```
- Verifique se os adicionados corresponde ao(s) criado(s) ```git status```
- Adicione um comentário relacionado a tela nova ```git commit -m "Tela nova de cadastro de ..."```
- Suba as alterações na branch criada ```git push origin feature/(NovaTela)```**sem parênteses**
- Por fim, abra a PR (Pull Request) acessando o link que será exibido após a execução do comando anterior.
