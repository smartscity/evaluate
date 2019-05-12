import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IEvaluation, Evaluation } from 'app/shared/model/evaluation.model';
import { EvaluationService } from './evaluation.service';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-evaluation-export',
  templateUrl: './evaluation-update.component.html'
})
export class EvaluationExportComponent implements OnInit {
  // evaluation: IEvaluation;
  // isSaving: boolean;

  // users: IUser[];

  // editForm = this.fb.group({
  //   id: [],
  //   speakerId: [],
  //   title: [],
  //   orgName: [],
  //   actor: [],
  //   speaker: [],
  //   level: [],
  //   path: [],
  //   taskSource: [],
  //   taskSourceScore: [],
  //   discoveryAndInnovation: [],
  //   discoveryAndInnovationScore: [],
  //   advancedLevel: [],
  //   advancedLevelScore: [],
  //   applicationAndPromotion: [],
  //   applicationAndPromotionScore: [],
  //   paperScore: [],
  //   replyScore: [],
  //   totalScore: [],
  //   avgScore: [],
  //   createTime: [],
  //   updateTime: [],
  //   evaluator: [],
  //   remark: [],
  //   user: []
  // });

  constructor(
    //   protected jhiAlertService: JhiAlertService,
    protected evaluationService: EvaluationService
  ) //   protected userService: UserService,
  //   protected activatedRoute: ActivatedRoute,
  //   private fb: FormBuilder
  {}

  ngOnInit() {
    this.evaluationService.export();
    // this.isSaving = false;
    // this.activatedRoute.data.subscribe(({ evaluation }) => {
    //   this.updateForm(evaluation);
    //   this.evaluation = evaluation;
    // });
    // this.userService
    //   .query()
    //   .pipe(
    //     filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
    //     map((response: HttpResponse<IUser[]>) => response.body)
    //   )
    //   .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  previousState() {
    window.history.back();
  }
}
