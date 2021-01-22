package com.letssolvetogether.omr.object;

import androidx.lifecycle.ViewModel;
import android.graphics.Bitmap;
import android.util.Log;

import org.opencv.core.Mat;

public class OMRSheet extends ViewModel {

    private static String TAG="OMRSheet";

    private Bitmap bmpOMRSheet;
    private Mat matOMRSheet;

    private int width;
    private int height;

    private OMRSheetCorners omrSheetCorners;
    private  OMRSheetBlock omrSheetBlock;

    private int numberOfQuestions; //모든 문제의 수
    private int optionsPerQuestions =5; //답변의 수
    private int questionsPerBlock = 16; //한 블럭의 줄의 수
    private int widthOfBoundingSquareForCircle;

    private int requiredBlackPixelsInBoundingSquare;
    private int totalPixelsInBoundingSquare;

    private int[] correctAnswers;

    public OMRSheet() {}

    public int getWidth(){
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Bitmap getBmpOMRSheet() {
        return bmpOMRSheet;
    }

    public void setBmpOMRSheet(Bitmap bmpOMRSheet) {
        this.bmpOMRSheet = bmpOMRSheet;
    }

    public OMRSheetCorners getOmrSheetCorners() {
        return omrSheetCorners;
    }

    public void setOmrSheetCorners(OMRSheetCorners omrSheetCorners) {
        this.omrSheetCorners = omrSheetCorners;
    }

    public OMRSheetBlock getOmrSheetBlock() {
        return omrSheetBlock;
    }

    public void setOmrSheetBlock() {

        int w = getWidth();
        int h = getHeight();

        omrSheetBlock = new OMRSheetBlock();




        omrSheetBlock.setBlockWidth((int)(w/3.2));
        omrSheetBlock.setBlockHeight((int)(h/1.39));
//        omrSheetBlock.setBlockWidth((int)(w/3.2));
//        omrSheetBlock.setBlockHeight((int)(h/1.39)); //길이와 너비



        omrSheetBlock.setxFirstBlockOffset((int)(w/1.597)); //1.4~
        omrSheetBlock.setyFirstBlockOffset((int)(h/5.4)); //1~9
//        omrSheetBlock.setxFirstBlockOffset((int)(w/6.1));
//        omrSheetBlock.setyFirstBlockOffset((int)(h/2.7)); //위치





        omrSheetBlock.setxDistanceBetweenBlock((int)(w/6.8)); //블럭 간 간격
        // omrSheetBlock.setxDistanceBetweenBlock((int)(w/6.3));
        omrSheetBlock.setyDistanceBetweenBlock(0);

        omrSheetBlock.setyDistanceBetweenRows((int)(h/49.0));



        omrSheetBlock.setxDistanceBetweenCircles((int)(w/15.77)); //원의 간격
        omrSheetBlock.setyDistanceBetweenCircles((int)(h/32.6));
//        omrSheetBlock.setxDistanceBetweenCircles((int)(w/14.77));
//        omrSheetBlock.setyDistanceBetweenCircles((int)(h/26.6));

    }

    public Mat getMatOMRSheet() {
        return matOMRSheet;
    }

    public void setMatOMRSheet(Mat matOMRSheet) {
        this.matOMRSheet = matOMRSheet;
    }

    public int getNumberOfBlocks() { //2개
        return numberOfQuestions/ questionsPerBlock;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions; //문제수 30으로 고정하기 위해 주석처리
    }

    public int getOptionsPerQuestions() {
        return optionsPerQuestions;
    }

    public int getWidthOfBoundingSquareForCircle() {
        widthOfBoundingSquareForCircle = (int)(getWidth()/20.0);//네모의 크기
//        widthOfBoundingSquareForCircle = (int)(getWidth()/17.0);
        //The width should be even otherwise we will get width difference of 1 in getTotalPixelsInBoundingSquare()
        // as we divide the width in getRectangleCoordinates()
        if(widthOfBoundingSquareForCircle % 2 != 0)
            widthOfBoundingSquareForCircle++;
        return widthOfBoundingSquareForCircle;
    }

    public int getQuestionsPerBlock() {
        return questionsPerBlock;
    }

    public int getTotalPixelsInBoundingSquare() {
        totalPixelsInBoundingSquare = getWidthOfBoundingSquareForCircle() * getWidthOfBoundingSquareForCircle();
        return totalPixelsInBoundingSquare;
    }

    public int getRequiredBlackPixelsInBoundingSquare() {
        requiredBlackPixelsInBoundingSquare = (int) (getTotalPixelsInBoundingSquare() * 0.22);
        return requiredBlackPixelsInBoundingSquare;
    }

    public int[] getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}