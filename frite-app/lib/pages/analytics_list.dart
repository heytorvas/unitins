import 'package:flutter/material.dart';
import 'package:frite/components/analytics_tile.dart';
import 'package:frite/models/analytics.dart';
import 'package:frite/services/analytics_service.dart';
import 'package:frite/utils/utils.dart';

class AnalyticsList extends StatefulWidget {
  const AnalyticsList({ Key? key }) : super(key: key);

  @override
  _AnalyticsListState createState() => _AnalyticsListState();
}

class _AnalyticsListState extends State<AnalyticsList> {

  List<Analytics> analytics = [];

  getAnalyticsList() async {
    List<Analytics> list = await AnalyticsService.findAll();
    setState(() {
      analytics = list;
      print(analytics);
    });
  }

  @override
  void initState() {
    super.initState();
    getAnalyticsList();
  } 
  
  final scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: appBar(context),
      key: scaffoldKey,
      backgroundColor: Theme.of(context).accentColor,
      body: ListView.builder(
            itemCount: analytics.length,
            itemBuilder: (context, index) => Card(   
              child: AnalyticsTile(analytics: analytics[index],),
            ),
            
      ),
    );
  }
}