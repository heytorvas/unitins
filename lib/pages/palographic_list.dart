import 'package:flutter/material.dart';
import 'package:frite/components/palographic_tile.dart';
import 'package:frite/models/palographic.dart';
import 'package:frite/pages/save_palographic_page.dart';
import 'package:frite/services/palographic_service.dart';
import 'package:frite/utils/utils.dart';

class PalographicList extends StatefulWidget {
  const PalographicList({ Key? key }) : super(key: key);

  @override
  _PalographicListState createState() => _PalographicListState();
}

class _PalographicListState extends State<PalographicList> {

  List<Palographic> palographic = [];

  getPalographicList() async {
    List<Palographic> list = await PalographicService.findAll();
    setState(() {
      palographic = list;
      print(palographic);
    });
  }

  @override
  void initState() {
    super.initState();
    getPalographicList();
  }  


  @override
  Widget build(BuildContext context) {
    final scaffoldKey = GlobalKey<ScaffoldState>();

    return Scaffold(
      appBar: appBar(context),
      key: scaffoldKey,
      backgroundColor: Theme.of(context).accentColor,
      body: ListView.builder(
            itemCount: palographic.length,
            itemBuilder: (context, index) => Card(
              // child: PsychologistTile(psychologists[index])      
              child: PalographicTile(palographic: palographic[index],),
            ),
            
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) =>
                    RegisterPalographic()));
        },
        child: const Icon(Icons.add, color: Colors.red),
        backgroundColor: Colors.white,
      ),
    );
  }
}