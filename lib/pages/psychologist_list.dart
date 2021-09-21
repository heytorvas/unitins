import 'package:flutter/material.dart';
import 'package:frite/components/psy_tile.dart';
import 'package:frite/components/psychologist_tile.dart';
import 'package:frite/models/psychologist.dart';
import 'package:frite/pages/save_psychologist_page.dart';
import 'package:frite/services/psychologist_service.dart';

class PsychologistList extends StatefulWidget {
  const PsychologistList({ Key? key }) : super(key: key);

  @override
  _PsychologistListState createState() => _PsychologistListState();
}

class _PsychologistListState extends State<PsychologistList> {

  List<Psychologist> psychologists = [];

  getPsychologistList() async {
    List<Psychologist> list = await PsychologistService.findAll();
    setState(() {
      psychologists = list;
      print(psychologists);
    });
    //return await List<Psychologist> PsychologistService().findAll();
  }
  
  @override
  void initState() {
    super.initState();

    getPsychologistList();
  }

  GlobalKey<FormState> globalFormKey = new GlobalKey<FormState>();
  final scaffoldKey = GlobalKey<ScaffoldState>();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: scaffoldKey,
      backgroundColor: Theme.of(context).accentColor,
      body: ListView.builder(
            itemCount: psychologists.length,
            itemBuilder: (context, index) => Card(
              // child: PsychologistTile(psychologists[index])      
              child: PsyTile(psychologist: psychologists[index],),
            ),
            
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) =>
                    RegisterPsychologist()));
        },
        child: const Icon(Icons.add, color: Colors.red),
        backgroundColor: Colors.white,
      ),
    );
  }
}