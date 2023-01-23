# No Banco XPTO, para o cálculo do comprometimento financeiro de uma empresa, leva-se em conta o total de bens imóveis desta empresa e de seus sócios. Escreva um método (e todas as classes adicionais) para retornar o total do comprometimento financeiro de uma empresa, passando como parâmetro a sua estrutura societária.

### Requisitos de Negócio:
 - A estrutura societária de uma empresa é sempre composta por ao menos uma pessoa física ou jurídica e pode ser composta por mais pessoas físicas e/ou jurídicas.
 - Toda empresa possui uma estrutura societária.
 - Pode haver ciclo societário, ou seja, a empresa A pode ser sócia da empresa B e a empresa B pode vir a ser sócia da empresa A. Inclusive, a empresa A pode vir a ser sócia dela mesma. Além disso, qualquer pessoa física ou jurídica pode ser sócia de mais de uma empresa.
 - A contabilização dos bens imóveis de uma pessoa (física ou jurídica) só deve ocorrer uma única vez.

## Compilar e rodar a aplicação:

Na pasta raiz do projeto abra um terminal (git bash, powershell, cmd)

## Execute o comando: 
mvn clean install

## Após compilar execute o comando:
java -Dfile.encoding=UTF-8 -jar br.com.diogolages-maven-0.0.1-SNAPSHOT-jar-with-dependencies.jar