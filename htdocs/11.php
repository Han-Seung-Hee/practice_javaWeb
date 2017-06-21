<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>11.php</title>
  </head>
  <body>
    <h1>Javascript</h1>
    <ul>
      <script type="text/javascript">
        list = new Array("a","b","c","d","e");
        i = 0;
        while(i<list.length){
          document.write("<li>"+list[i]+"</li>");
          i = i+1;
        }

      </script>

    </ul>

    <h1>php</h1>
    <ul>
      <?php
        $list = array("f","g","h","i","j");
        $i = 0;
        while($i < count($list)){
          echo "<li>".$list[$i]."</li>";
          $i = $i + 1;
        }
       ?>
    </ul>
  </body>
</html>
