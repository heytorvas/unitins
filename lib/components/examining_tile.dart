import 'package:flutter/material.dart';
import 'package:frite/models/examining.dart';
import 'package:frite/pages/examining_list.dart';
import 'package:frite/services/examining_service.dart';

class ExaminingTile extends StatefulWidget {
  final Examining examining;
  const ExaminingTile({ Key? key, required this.examining }) : super(key: key);

  @override
  _ExaminingTileState createState() => _ExaminingTileState();
}

class _ExaminingTileState extends State<ExaminingTile> {

  delete() async {
    await ExaminingService.delete(widget.examining.id);
    Navigator.push(
      context,
      MaterialPageRoute(
          builder: (context) =>
              ExaminingList()));
  }
  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(widget.examining.name!),
      subtitle: Text(widget.examining.cpf!),
      onTap: () {
        showDialog(
          context: context, 
          builder: (ctx) => AlertDialog(
            title: Text('Examinado'),
            content: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Nome: ${widget.examining.name}'),
                Text('Sexo: ${widget.examining.gender}'),
                Text('CPF: ${widget.examining.cpf}'),
                Text('Data de Nascimento: ${widget.examining.birthDate}'),
                Text('Escolaridade: ${widget.examining.schooling}'),
                Text('Cidade: ${widget.examining.city}'),
                Text('Estado: ${widget.examining.state}'),
              ],
            ),
            actions: <Widget>[
              TextButton(
                onPressed: () => Navigator.of(context).pop(false), 
                child: Text('Fechar'),
              ),
            ],
          )
        );
      },
      trailing: Container(
        width: 100,
        child: Row(
          children: <Widget>[
            IconButton(
              icon: Icon(Icons.edit),
              color: Colors.orange,
              onPressed: () {},
            ),
            IconButton(
              icon: Icon(Icons.delete),
              color: Colors.red,
              onPressed: () {
                showDialog(
                  context: context, 
                  builder: (ctx) => AlertDialog(
                    title: Text('Excluir Examinado'),
                    content: Text('Tem certeza?'),
                    actions: <Widget>[
                      TextButton(
                        onPressed: () => Navigator.of(context).pop(false), 
                        child: Text('Não'),
                      ),
                      TextButton(
                        onPressed: () => Navigator.of(context).pop(true), 
                        child: Text('Sim'),
                      ),
                    ],
                  )
                ).then((confirmed) {
                  if (confirmed) {
                    delete();
                  }
                });
              },
            )
          ],
        ),
      ) 
    ); 
  }
}