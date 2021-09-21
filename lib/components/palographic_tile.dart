import 'package:flutter/material.dart';
import 'package:frite/models/palographic.dart';

class PalographicTile extends StatefulWidget {
  final Palographic palographic;
  const PalographicTile({ Key? key, required this.palographic }) : super(key: key);

  @override
  _PalographicTileState createState() => _PalographicTileState();
}

class _PalographicTileState extends State<PalographicTile> {
  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(widget.palographic.examining!.name!),
      subtitle: Text(widget.palographic.psychologist!.name!),
      onTap: () {
        showDialog(
          context: context, 
          builder: (ctx) => AlertDialog(
            title: Text('Palográfico'),
            content: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Examinado: ${widget.palographic.examining!.name}'),
                Text('Psicologo: ${widget.palographic.psychologist!.name}'),
                Text('Data do Teste: ${widget.palographic.testDate}'),
                Text('Escolaridade: ${widget.palographic.schooling}'),
                Text('Foto: ${widget.palographic.test}'),
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
                    title: Text('Excluir Palográfico'),
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
                    print('F');
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