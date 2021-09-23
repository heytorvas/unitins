import 'package:flutter/material.dart';
import 'package:frite/models/analytics.dart';
import 'package:frite/pages/analytics_list.dart';
import 'package:frite/services/analytics_service.dart';
import 'package:frite/utils/const.dart';

class AnalyticsTile extends StatefulWidget {
  final Analytics analytics;
  const AnalyticsTile({Key? key, required this.analytics}) : super(key: key);

  @override
  _AnalyticsTileState createState() => _AnalyticsTileState();
}

class _AnalyticsTileState extends State<AnalyticsTile> {
  getUrlImage(String path) {
    String fileName = path.split('/').last;
    return '$API_IMAGE/media/images/$fileName';
  }

  delete() async {
    await AnalyticsService.delete(widget.analytics.id!);
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => AnalyticsList()));
  }

  @override
  Widget build(BuildContext context) {
    return ListTile(
        title: Text(widget.analytics.examining!.name!),
        subtitle: Text("Psicólogo: ${widget.analytics.psychologist!.name!}"),
        onTap: () {
          showDialog(
              context: context,
              builder: (ctx) => AlertDialog(
                    title: Text('Palográfico'),
                    content: Container(
                      child: SingleChildScrollView(
                        child: Column(
                          mainAxisSize: MainAxisSize.min,
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            ClipRRect(
                              child: Image.network(
                                getUrlImage(
                                    '${widget.analytics.palographic!.test}'),
                                fit: BoxFit.cover,
                              ),
                            ),
                            Text(
                                'Examinado: ${widget.analytics.examining!.name}'),
                            Text(
                                'Psicologo: ${widget.analytics.psychologist!.name}'),
                            Text(
                                'Data do Teste: ${widget.analytics.palographic!.testDate}'),
                            Text(
                                'Produtividade: ${widget.analytics.productivity}'),
                            Text('NOR: ${widget.analytics.nor}'),
                            Text(
                                'Palos por Intervalo: ${widget.analytics.income}'),
                            Text(
                                'Distância entre os Palos: ${widget.analytics.distanceBetweenPalos}'),
                            Text(
                                'Tamanho dos Palos: ${widget.analytics.palosSize}'),
                            Text(
                                'Impulsividade: ${widget.analytics.impulsiveness}'),
                            Text(
                                'Soma da diferença: ${widget.analytics.differenceSum}'),
                          ],
                        ),
                      ),
                    ),
                    actions: <Widget>[
                      TextButton(
                        onPressed: () => Navigator.of(context).pop(false),
                        child: Text('Fechar'),
                      ),
                    ],
                  ));
        },
        trailing: Container(
          width: 50,
          child: Row(
            children: <Widget>[
              IconButton(
                icon: Icon(Icons.delete),
                color: Colors.red,
                onPressed: () {
                  showDialog(
                      context: context,
                      builder: (ctx) => AlertDialog(
                            title: Text('Excluir Análise'),
                            content: Text('Tem certeza?'),
                            actions: <Widget>[
                              TextButton(
                                onPressed: () =>
                                    Navigator.of(context).pop(false),
                                child: Text('Não'),
                              ),
                              TextButton(
                                onPressed: () =>
                                    Navigator.of(context).pop(true),
                                child: Text('Sim'),
                              ),
                            ],
                          )).then((confirmed) {
                    if (confirmed) {
                      delete();
                    }
                  });
                },
              )
            ],
          ),
        ));
  }
}
