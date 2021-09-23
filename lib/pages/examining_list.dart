import 'package:flutter/material.dart';
import 'package:frite/components/examining_tile.dart';
import 'package:frite/models/examining.dart';
import 'package:frite/pages/save_examining_page.dart';
import 'package:frite/services/examining_service.dart';
import 'package:frite/utils/utils.dart';

class ExaminingList extends StatefulWidget {
  const ExaminingList({ Key? key }) : super(key: key);

  @override
  _ExaminingListState createState() => _ExaminingListState();
}

class _ExaminingListState extends State<ExaminingList> {
  List<Examining> examining = [];

  getExaminingList() async {
    List<Examining> list = await ExaminingService.findAll();
    setState(() {
      examining = list;
      print(examining);
    });
  }

  @override
  void initState() {
    super.initState();
    getExaminingList();
  }  
  
  GlobalKey<FormState> globalFormKey = new GlobalKey<FormState>();
  final scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: appBar(context),
      key: scaffoldKey,
      backgroundColor: Theme.of(context).accentColor,
      body: ListView.builder(
            itemCount: examining.length,
            itemBuilder: (context, index) => Card(
              // child: PsychologistTile(psychologists[index])      
              child: ExaminingTile(examining: examining[index],),
            ),
            
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) =>
                    RegisterExamining()));
        },
        child: const Icon(Icons.add, color: Colors.red),
        backgroundColor: Colors.white,
      ),
    );
  }
}