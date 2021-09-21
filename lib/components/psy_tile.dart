import 'package:flutter/material.dart';
import 'package:frite/models/psychologist.dart';
import 'package:frite/pages/psychologist_list.dart';
import 'package:frite/services/psychologist_service.dart';

class PsyTile extends StatefulWidget {
  final Psychologist psychologist;
  const PsyTile ({Key? key, required this.psychologist }):super(key: key);

  @override
  _PsyTileState createState() => _PsyTileState();
}

class _PsyTileState extends State<PsyTile> {

  delete() async {
    await PsychologistService.delete(widget.psychologist.id);
    Navigator.push(
      context,
      MaterialPageRoute(
          builder: (context) =>
              PsychologistList()));
  }

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(widget.psychologist.name!),
      subtitle: Text(widget.psychologist.email!),
      onTap: () {
        showDialog(
          context: context, 
          builder: (ctx) => AlertDialog(
            title: Text('Psicólogo'),
            content: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Nome: ${widget.psychologist.name}'),
                Text('Email: ${widget.psychologist.email}'),
                Text('CPF: ${widget.psychologist.cpf}'),
                Text('Data de Nascimento: ${widget.psychologist.birthDate}')
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
                    title: Text('Excluir Psicólogo'),
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