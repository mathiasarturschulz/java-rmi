# java-rmi
Exemplo de aplicação utilizando Java RMI (Remote Method Invocation), mecanismo de invocação de métodos que estão em diferentes máquinas virtuais Java (JVM) e podem estar em diferentes máquinas

O cliente pode adicionar um telefone na agenda de telefones e listar os telefones já cadastrados

## Compilar

Para compilar a aplicação, na pasta java-rmi do projeto execute o comando abaixo

`javac -d ./ phonebook/*.java`

O projeto compilado estará salvo na pasta *classapp*


## Executar

Primeiro é necessário executar o servidor

`java classapp.PhoneBookServer`

Depois é necessário executar o cliente

`java classapp.PhoneBookClient`

**Obs:** É necessário alterar o diretório no qual o *data.txt* é lido e gravado

