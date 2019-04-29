/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { EvaluationService } from 'app/entities/evaluation/evaluation.service';
import {
  IEvaluation,
  Evaluation,
  Level,
  TaskSource,
  DiscoveryAndInnovation,
  AdvancedLevel,
  ApplicationAndPromotion
} from 'app/shared/model/evaluation.model';

describe('Service Tests', () => {
  describe('Evaluation Service', () => {
    let injector: TestBed;
    let service: EvaluationService;
    let httpMock: HttpTestingController;
    let elemDefault: IEvaluation;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(EvaluationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Evaluation(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Level.FIRST,
        TaskSource.NATIONAL_PLAN,
        0,
        DiscoveryAndInnovation.HIGH,
        0,
        AdvancedLevel.LEADING,
        0,
        ApplicationAndPromotion.EXCELLENCE,
        0,
        0,
        0,
        0,
        0,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            createTime: currentDate.format(DATE_TIME_FORMAT),
            updateTime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Evaluation', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createTime: currentDate.format(DATE_TIME_FORMAT),
            updateTime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createTime: currentDate,
            updateTime: currentDate
          },
          returnedFromService
        );
        service
          .create(new Evaluation(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Evaluation', async () => {
        const returnedFromService = Object.assign(
          {
            speakerId: 1,
            title: 'BBBBBB',
            orgName: 'BBBBBB',
            actor: 'BBBBBB',
            speaker: 'BBBBBB',
            level: 'BBBBBB',
            taskSource: 'BBBBBB',
            taskSourceScore: 1,
            discoveryAndInnovation: 'BBBBBB',
            discoveryAndInnovationScore: 1,
            advancedLevel: 'BBBBBB',
            advancedLevelScore: 1,
            applicationAndPromotion: 'BBBBBB',
            applicationAndPromotionScore: 1,
            paperScore: 1,
            replyScore: 1,
            totalScore: 1,
            avgScore: 1,
            createTime: currentDate.format(DATE_TIME_FORMAT),
            updateTime: currentDate.format(DATE_TIME_FORMAT),
            evaluator: 'BBBBBB',
            remark: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createTime: currentDate,
            updateTime: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Evaluation', async () => {
        const returnedFromService = Object.assign(
          {
            speakerId: 1,
            title: 'BBBBBB',
            orgName: 'BBBBBB',
            actor: 'BBBBBB',
            speaker: 'BBBBBB',
            level: 'BBBBBB',
            taskSource: 'BBBBBB',
            taskSourceScore: 1,
            discoveryAndInnovation: 'BBBBBB',
            discoveryAndInnovationScore: 1,
            advancedLevel: 'BBBBBB',
            advancedLevelScore: 1,
            applicationAndPromotion: 'BBBBBB',
            applicationAndPromotionScore: 1,
            paperScore: 1,
            replyScore: 1,
            totalScore: 1,
            avgScore: 1,
            createTime: currentDate.format(DATE_TIME_FORMAT),
            updateTime: currentDate.format(DATE_TIME_FORMAT),
            evaluator: 'BBBBBB',
            remark: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createTime: currentDate,
            updateTime: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Evaluation', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
