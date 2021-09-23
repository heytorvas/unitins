import 'package:flutter/material.dart';
import 'package:frite/models/psychologist.dart';
import 'package:frite/pages/update_psychologist_page.dart';
import 'package:frite/services/psychologist_service.dart';

class PsychologistTile extends StatelessWidget {

  final Psychologist psychologist;
  const PsychologistTile(this.psychologist);

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(psychologist.name!),
      subtitle: Text(psychologist.email!),
      trailing: Container(
        width: 100,
        child: Row(
          children: <Widget>[
            IconButton(
              icon: Icon(Icons.edit),
              color: Colors.orange,
              onPressed: () {
                Navigator.push(
                    context, MaterialPageRoute(builder: (context) => UpdatePsychologist(psychologist: psychologist)));
              },
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
                    PsychologistService.delete(psychologist.id);
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