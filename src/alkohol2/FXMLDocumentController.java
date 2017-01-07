/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alkohol2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Ondra
 */
public class FXMLDocumentController implements Initializable {

    private final int _CalcDepth = 6;
    private int _ComputingRightNowID = -1;

    @FXML
    private Canvas CanvasID;
    @FXML
    private TextField txtOrigPercentage;
    @FXML
    private TextField txtOutputPercentage;
    @FXML
    private TextField txtOrigVolume;
    @FXML
    private TextField txtOutputVolume;
    @FXML
    private TextField txtJuiceValue;

    private int _OrigPercentage = -1;
    private int _OutputPercentage = -1;
    private int _OrigVolume = -1;
    private int _OutputVolume = -1;
    private int _JuiceValue = -1;
    @FXML
    private TextField txtPureAlcoholValue;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public int getOrigPercentage() {
        return getOrigPercentage(0);
    }

    public int getOutputPercentage() {
        return getOutputPercentage(0);
    }

    public int getOrigVolume() {
        return getOrigVolume(0);
    }

    public int getOutputVolume() {
        return getOutputVolume(0);
    }

    public int getJuiceValue() {
        return getJuiceValue(0);
    }

    public int getPureAlcoholValue() {
        return getPureAlcoholValue(0);
    }

    public int getOrigPercentage(int actualDepth) {//a
        SOUT_myOutput_nepodstatne("getOrigPercentage");
        if (_ComputingRightNowID != 0) {
            try {
                return Integer.valueOf(txtOrigPercentage.getText());
            } catch (Exception e) {
//                return -1;
            }
        }
        if (actualDepth++ < _CalcDepth) {
            int a, b, c, d, f;
            b = getOutputPercentage(actualDepth);
            c = getOrigVolume(actualDepth);
            d = getOutputVolume(actualDepth);

            f = getPureAlcoholValue(actualDepth);

            if ((b | c | d) > 0) {
                int tmp = b * d / c;
                return tmp;
            } else if ((f | c) > 0) {
                return 100 * f / c;
            }
            return -1;

        }

        return -1;
    }

    public int getOutputPercentage(int actualDepth) {//b
        SOUT_myOutput_nepodstatne("getOutputPercentage");
        if (_ComputingRightNowID != 1) {
            try {
                return Integer.valueOf(txtOutputPercentage.getText());
            } catch (Exception e) {
//                return -1;
            }
        }
        if (actualDepth++ < _CalcDepth) {
            int a, b, c, d,f;
            a = getOrigPercentage(actualDepth);
            c = getOrigVolume(actualDepth);
            d = getOutputVolume(actualDepth);

            f = getPureAlcoholValue(actualDepth);
            if ((a | c | d) > 0) {
                int tmp = a * c / d;
                return tmp;
            } else if ((f | d)>0){
                return 100*f/d;
            }
            return -1;

        }
        return -1;
    }

    public int getOrigVolume(int actualDepth) {
        SOUT_myOutput_nepodstatne("getOrigVolume");
        if (_ComputingRightNowID != 2) {
            try {
                return Integer.valueOf(txtOrigVolume.getText());
            } catch (Exception e) {
//                return -1;
            }
        }
        if (actualDepth++ < _CalcDepth) {
            int a, b, c, d, e;
            a = getOrigPercentage(actualDepth);
            b = getOutputPercentage(actualDepth);
            d = getOutputVolume(actualDepth);

            e = getJuiceValue(actualDepth);

            if ((a | b | d) >= 0) {
                int tmp = d * b / a;
                return tmp;
            } else if ((e | d) > 0) {
                return d - e;
            }

            return -1;
        }
        return -1;
    }

    public int getOutputVolume(int actualDepth) {
        SOUT_myOutput_nepodstatne("getOutputVolume");
        if (_ComputingRightNowID != 3) {
            try {
                return Integer.valueOf(txtOutputVolume.getText());
            } catch (Exception e) {
//                return -1;
            }
        }
        if (actualDepth++ < _CalcDepth) {
            int a, b, c, d, e;
            a = getOrigPercentage(actualDepth);
            b = getOutputPercentage(actualDepth);
            c = getOrigVolume(actualDepth);

            e = getJuiceValue(actualDepth);

            if ((a | b | c) >= 0) {
                int tmp = a * c / b;
                return tmp;
            } else if ((c | e) >= 0) {
                return c + e;
            }

            return -1;
        }
        return -1;
    }

    public int getJuiceValue(int actualDepth) {
        SOUT_myOutput_nepodstatne("getJuiceValue");
        if (_ComputingRightNowID != 4) {
            try {
                return Integer.valueOf(txtJuiceValue.getText());
            } catch (Exception e) {
//                return -1;
            }
        }
        if (actualDepth++ < _CalcDepth) {
            int a, b, c, d;
            c = getOrigVolume(actualDepth);
            d = getOutputVolume(actualDepth);

            if ((c | d) < 0) {
                return -1;
            }

            int tmp = d - c;
            return tmp;
        }
        return -1;
    }

    public int getPureAlcoholValue(int actualDepth) {
        SOUT_myOutput_nepodstatne("getPureAlcoholValue");
        if (_ComputingRightNowID != 5) {
            try {
                return Integer.valueOf(txtPureAlcoholValue.getText());
            } catch (Exception e) {
//                return -1;
            }
        }
        if (actualDepth++ < _CalcDepth) {
            int a, b, c, d;
            a = getOrigPercentage(actualDepth);
            b = getOutputPercentage(actualDepth);
            c = getOrigVolume(actualDepth);
            d = getOutputVolume(actualDepth);

            if ((a | c) >= 0) {
                int tmp = c * a / 100;
                return tmp;
            } else if ((b | d) >= 0) {
                int tmp = b * d / 100;
                return tmp;
            }
            return -1;

        }
        return -1;
    }

    public void setTxtOrigPercentageTxt(int input) {
        this.txtOrigPercentage.setText(input >0 ? Integer.toString(input) : "");
    }

    public void setTxtOutputPercentageTxt(int input) {
        this.txtOutputPercentage.setText(input >0 ? Integer.toString(input) : "");
    }

    public void setTxtOrigVolumeTxt(int input) {
        this.txtOrigVolume.setText(input >0 ? Integer.toString(input) : "");
    }

    public void setTxtOutputVolumeTxt(int input) {
        this.txtOutputVolume.setText(input >0 ? Integer.toString(input) : "");
    }

    public void setTxtJuiceValueTxt(int input) {
        this.txtJuiceValue.setText(input >0  ? Integer.toString(input) : "");
    }

    public void setTxtPureAlcoholValue(int input) {
        this.txtPureAlcoholValue.setText(input >0 ? Integer.toString(input) : "");
    }
//tramtarárieeee
    @FXML
    private void btnComputeAction0(ActionEvent event) {
        _ComputingRightNowID = 0;
        setTxtOrigPercentageTxt(getOrigPercentage());
        _ComputingRightNowID = -1;
        AnimateWhenComputable();
        System.out.println("testikkkkkkks");
    }

    @FXML
    private void btnComputeAction1(ActionEvent event) {
        _ComputingRightNowID = 1;
        setTxtOutputPercentageTxt(getOutputPercentage());
        _ComputingRightNowID = -1;
        AnimateWhenComputable();
    }

    @FXML
    private void btnComputeAction2(ActionEvent event) {
        _ComputingRightNowID = 2;
        setTxtOrigVolumeTxt(getOrigVolume());
        _ComputingRightNowID = -1;
        AnimateWhenComputable();
    }

    @FXML
    private void btnComputeAction3(ActionEvent event) {
        _ComputingRightNowID = 3;
        setTxtOutputVolumeTxt(getOutputVolume());
        _ComputingRightNowID = -1;
        AnimateWhenComputable();
    }

    @FXML
    private void btnComputeAction4(ActionEvent event) {
        _ComputingRightNowID = 4;
        setTxtJuiceValueTxt(getJuiceValue());
        _ComputingRightNowID = -1;
        AnimateWhenComputable();
    }

    @FXML
    private void btnComputeAction5(ActionEvent event) {
        _ComputingRightNowID = 5;
        setTxtPureAlcoholValue(getPureAlcoholValue());
        _ComputingRightNowID = -1;
        AnimateWhenComputable();
    }

//muhehehe
//muhehehe   
//muhehehe----------------------------------------------------------------------------------------------------
//muhehehe----------------------------------------------------------------------------------------------------
//muhehehe----------------------------------------------------------------------------------------------------
//muhehehe----------------------------------------------------------------------------------------------------
//muhehehe
//muhehehe
    private void SOUT_myOutput_nepodstatne(Object str) {
        System.out.println(str);
    }

    private void SOUT_myOutput_nepodstatne(Object str, boolean error) {
        if (error) {
            System.err.println(str);
        } else {
            System.out.println(str);
        }
    }

    private void SOUT_myOutput_nepodstatne(Object str, int error) {
        if (error == 1) {
            SOUT_myOutput_nepodstatne(str, true);
        } else {
            SOUT_myOutput_nepodstatne(str, false);
        }
    }

    //----------------------------------
    AnimationTimer loop;

    private void startAnimation() {

        if ((getOrigVolume(1) | getOutputVolume(1) | getOutputPercentage(1)) < 0) {
            return;
        }

        try {
            loop.stop();
            System.out.println("__animation stopped__");
        } catch (Exception e) {
        }

        GraphicsContext graphicsContext;
        graphicsContext = CanvasID.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, CanvasID.getWidth(), CanvasID.getHeight());

        loop = new AnimationTimer() {

            double speed = 0.4;

            int panakBudeOdStranVzdalen = 20;
            int pruchodu = 53;//pruchody animace

            double uhel_setinMimo = 30;// setiny .. cim vic, tim roztahlejsi panak

            double x1 = CanvasID.getWidth() * uhel_setinMimo / 100;
            double x2 = CanvasID.getWidth() - CanvasID.getWidth() * uhel_setinMimo / 100;
            double x3 = x2;
            double x4 = x1;

            double y1 = CanvasID.getHeight() - panakBudeOdStranVzdalen;
            double y2 = y1;
            double y3 = y1;
            double y4 = y1;

            double vysledneY3 = panakBudeOdStranVzdalen;
            double vysledneX3 = CanvasID.getWidth() - panakBudeOdStranVzdalen;

            @Override
            public void handle(long now) {
                graphicsContext.setLineWidth(1);

                graphicsContext.clearRect(0, 0, CanvasID.getWidth(), CanvasID.getHeight());

//                graphicsContext.fillOval(x, y, 5, 5);
                //DZUS: --------------
                y3 -= ((y3 - vysledneY3) / pruchodu);
                y4 = y3; //((y4 - vysledneY3) / pruchodu);
                double tmp = ((CanvasID.getWidth() - vysledneX3) / pruchodu);
                x3 += tmp;
                x4 -= tmp;

                graphicsContext.setFill(Color.YELLOW);
                graphicsContext.fillPolygon(new double[]{x1, x2, x3, x4}, new double[]{y1, y2, y3, y4}, 4);

                //alkoholicky napoj (-vodka): ------------------
                double tmp2 = (double) getOrigVolume(1) / (double) getOutputVolume(1);

                graphicsContext.setFill(Color.AQUAMARINE);
                graphicsContext.fillPolygon(new double[]{x1, x2, x2 + tmp2 * (x3 - x2), x1 - tmp2 * (x3 - x2)},
                        new double[]{y1, y2, y2 - tmp2 * (y2 - y3), y2 - tmp2 * (y2 - y3)}, 4);

                //cisty alkohol!: -----------------
                double tmp3 = ((double) getOutputPercentage(1) / 100) % 101;

                graphicsContext.setFill(Color.AQUA);
                graphicsContext.fillPolygon(new double[]{x1, x2, x2 + tmp3 * (x3 - x2), x1 - tmp3 * (x3 - x2)},
                        new double[]{y1, y2, y2 - tmp3 * (y2 - y3), y2 - tmp3 * (y2 - y3)}, 4);

                //panák ! : ---------------------
                graphicsContext.setLineWidth(3);
                graphicsContext.setFill(Color.SLATEGREY);
                int mezera = 0;
                int intTloustky = 10;
                int upityPanak = 10;

                graphicsContext.fillPolygon(new double[]{x1, x2, x3 + mezera, x3 + mezera + intTloustky, x2 + intTloustky / 1.41,
                    x1 - intTloustky / 1.41, x4 - mezera - intTloustky, x4 - mezera},
                        new double[]{y1 + mezera, y2 + mezera, y3 - upityPanak, y3 - upityPanak, y2 + intTloustky / 1.41,
                            y2 + intTloustky / 1.41, y4 - upityPanak, y4 - upityPanak}, 8);
                
                
                //vršek tekutiny ! : -------------------------
                int width=27;
                graphicsContext.setFill(Color.web(("rgb(244,244,244)")));
                graphicsContext.fillOval(x4+2, y3-width/2, x3-x4-5, width);
                

//                SOUT_myOutput_nepodstatne(x1 + " " + x2 + " " + x3 + " " + x4 + " " + y1 + " " + y2 + " " + y3 + " " + y4);
//                SOUT_myOutput_nepodstatne(CanvasID.getWidth());
                if (y3 < panakBudeOdStranVzdalen + 6) {
                    loop.stop();
                }
            }
        };

        loop.start();
        System.out.println("__Animation started__");

    }

    private void AnimateWhenComputable() {
        boolean bol = tryComputeEverything();
        System.out.println("animation: " + bol);
        if (bol) {
            System.out.println("lol");
            startAnimation();
        }
    }

    private boolean tryComputeEverything() {
        System.out.println("pure: " + getPureAlcoholValue(1));
        return ((getOutputVolume(1) | getOrigPercentage(1) | getOrigVolume(1) | getOutputPercentage(1)) > 0); //...| getOutputVolume(1) | getPureAlcoholValue(1)
    }

}
