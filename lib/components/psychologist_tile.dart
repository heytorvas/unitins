import 'package:flutter/material.dart';
import 'package:frite/models/psychologist.dart';

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
              onPressed: () {},
            ),
            IconButton(
              icon: Icon(Icons.delete),
              color: Colors.red,
              onPressed: () {},
            )
          ],
        ),
      ) 
    );      



    // return ListTile(
    //   title: Text(psychologist.name!,
    //   style: TextStyle(
    //     color: Colors.green,
    //     fontSize: 40.0,
    //     fontWeight: FontWeight.w800,
    //   )),
      // title: Text(psychologist.name!),
      // subtitle: Text(psychologist.email!),
      // trailing: Container(
      //   width: 100,
      //   child: Row(
      //     children: <Widget>[
      //       IconButton(
      //         icon: Icon(Icons.edit),
      //         color: Colors.orange,
      //         onPressed: () {},
      //       ),
      //       IconButton(
      //         icon: Icon(Icons.delete),
      //         color: Colors.red,
      //         onPressed: () {},
      //       )
      //     ],
      //   ),
      // ),
  }
}