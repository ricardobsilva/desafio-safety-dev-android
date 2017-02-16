
   O teste consiste em criar um aplicativo que irá sugerir  linguagens de programação exibidas de maneira aleatória, vindas de uma API que encontra-se [aqui](https://teste-safety.herokuapp.com/api/v1/suggestions), dando ao usuário a opção de enviar suas próprias sugestões de linguagem e o motivo que o faz achar que essa linguagem deveria ser estudada. 
   	
   Uma especificação básica segue abaixo. Sinta-se livre para implementar como bem entender tudo o que não for especificado, isso também será avaliado.

## Rotas da API

### GET[/api/v1/suggestions]

> - Esse serviço trará uma sugestão de linguagem por vez, de maneira aleatória, contendo id, name, description,   IMEI (número de identificação único do celular) , reason_to_learn da linguagem em questão e username(nome do usuário que a cadastrou) como atributos. O usuário terá a opção de solicitar uma nova sugestão caso não goste da que foi dada.

 **obs**: Uma nova sugestão de linguagem será solicitada quando o usuário apertar e segurar por alguns segundos o botão de volume(baixo) do aparelho. **Utilizar broadcast receivers para isso**.	

```     
{
  "id": 5,
  "name": "Java",
  "description": "Java é uma linguagem de programação interpretada orientada a objetos desenvolvida na década de 90 ",
  "imei": "478954564798445",
  "reason_to_learn": "Java te permite criar aplicações para várias plataformas diferentes...",
  "username": "Bruce Wayne",
  "created_at": "2017-02-16T17:48:20.158Z",
  "updated_at": "2017-02-16T17:48:20.158Z"
}



```

### POST[/api/v1/suggestions]

> - Através desse serviço o aplicativo enviará uma sugestão de linguagem de programação a ser estudada . A requisição para esse serviço conterá os seguintes atributos: name, description,  IMEI (número de identificação único do celular), reason_to_learn e username


 **obs**: Não se preocupe com a possibilidade de uma linguagem ser cadastrada mais de uma vez.


```     
** Exemplo de como o Json deverá ser enviado para o serviço 

{
    "suggestion": {
	  "name": "JavaScript",
	  "description": "JavaScript é uma linguagem de programação interpretada.",
	  "imei": "478954564798445",
	  "reason_to_learn": "Javascript permite você usar a mesma linguagem tanto no Front-End quando no Back-End...",
	  "username": "Bruce Wayne"
    }
}







** Após salvar o objeto Json, esse será o retorno:

{
  "id": 3,
  "name": "JavaScript",
  "description": "JavaScript é uma linguagem de programação interpretada.",
  "imei": "478954564798445",
  "reason_to_learn": "Javascript permite você usar a mesma linguagem tanto no Front-End quando no Back-End...",
  "username": "Bruce Wayne",
  "created_at": "2017-02-16T17:48:20.158Z",
  "updated_at": "2017-02-16T17:48:20.158Z"
}


```


## Autenticação
> - Para acessar as funções do aplicativo, o usuário deverá passar  por uma tela de autenticação. Essa autenticação será feita através de um botão para logar com Facebook OU Linkedin, ficando a seu critério de escolha. É com o retorno do nome do usuário logado, vindo do retorno dos dados de autenticação  que o atributo username será preenchido na hora da criação de uma nova sugestão de linguagem de programação a ser aprendida. 


## Versões do Android
> - A partir da API 22(Android 5.1)


## Recomendações

> - Desconsideraremos entregas após o prazo estipulado
> - O horário do PR criado será considerado como hora da entrega
> - Não é obrigatório, mas criar testes para seu código será um plus

	



# E agora?

Agora é a fazer. Faça um fork desse repositório e envie seu pull-request SOMENTE quando concluir seu aplicativo.  

> - Entre em contato caso tenha alguma dúvida sobre as especificações.


Boa sorte!









