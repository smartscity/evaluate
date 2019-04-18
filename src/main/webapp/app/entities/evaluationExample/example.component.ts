import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
// import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISpeaker } from 'app/shared/model/speaker.model';
import { AccountService } from 'app/core';
import { SpeakerService } from './example.service';

@Component({
  selector: 'jhi-speaker',
  templateUrl: './example.component.html'
})
export class ExampleComponent implements OnInit, OnDestroy {
  speakers: any[];
  currentAccount: any;
  eventSubscriber: Subscription;
  taskOptions: any[];
  taskShow: boolean;
  taskTitel: string;
  discoveryOptions: any[];
  discoveryShow: boolean;
  discoveryTitel: string;
  levelOptions: any[];
  levelShow: boolean;
  levelTitel: string;
  applicationOptions: any[];
  applicationShow: boolean;
  applicationTitel: string;
  totalNumber: number;

  constructor(
    protected speakerService: SpeakerService,
    // protected jhiAlertService: JhiAlertService,
    // protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.speakers = [
      {
        id: 1,
        title: '中国脑性瘫痪康复指南(2015)',
        orgName: '中国康复医学会儿童康复专业委员会',
        actor: '李晓捷，唐久来',
        speak: '44',
        level: '一等奖',
        pdf: '66',
        taskSource: '',
        taskSourceScore: '',
        discoveryAndInnovation: '',
        discoveryAndInnovationScore: '',
        advancedLevel: '',
        advancedLevelScore: '',
        applicationAndPromotion: '',
        applicationAndPromotionScore: '',
        paper: '',
        paperScore: '',
        reply: '',
        replyScore: ''
      },
      {
        id: 2,
        title: '脑卒中后认知障碍康复评价及关健技术研究',
        orgName: '福建中医学大学',
        actor: '陈立典',
        speak: '44',
        level: '一等奖',
        pdf: '66',
        taskSource: '',
        taskSourceScore: '',
        discoveryAndInnovation: '',
        discoveryAndInnovationScore: '',
        advancedLevel: '',
        advancedLevelScore: '',
        applicationAndPromotion: '',
        applicationAndPromotionScore: '',
        paper: '',
        paperScore: '',
        reply: '',
        replyScore: ''
      }
    ];

    // this.speakerService
    //   .query()
    //   .pipe(
    //     filter((res: HttpResponse<ISpeaker[]>) => res.ok),
    //     map((res: HttpResponse<ISpeaker[]>) => res.body)
    //   )
    //   .subscribe(
    //     (res: ISpeaker[]) => {
    //       this.speakers = res;
    //     },
    //     (res: HttpErrorResponse) => this.onError(res.message)
    //   );
  }

  ngOnInit() {
    this.loadAll();
    this.taskOptions = [
      { label: '国家计划', value: '1' },
      { label: '部委计划', value: '2' },
      { label: '省（自治区、市）计划', value: '3' },
      { label: '基金资助', value: '4' },
      { label: '国际合作', value: '5' },
      { label: '其他企事业单位委托', value: '6' },
      { label: '自选', value: '7' },
      { label: '其他', value: '8' }
    ];
    this.discoveryOptions = [
      { label: '重大发现和显著创新', value: '1' },
      { label: '重要发现和明显创新', value: '2' },
      { label: '新发现和一般创新', value: '3' }
    ];
    this.levelOptions = [{ label: '领先水平', value: '1' }, { label: '先进水平', value: '2' }, { label: '一般水平', value: '3' }];
    this.applicationOptions = [
      { label: '应用推广及效益显著', value: '1' },
      { label: '应用推广及效益明显', value: '2' },
      { label: '应用推广及效益一般', value: '3' }
    ];

    // this.accountService.identity().then(account => {
    //   this.currentAccount = account;
    // });
    // this.registerChangeInSpeakers();
  }

  ngOnDestroy() {
    //   this.eventManager.destroy(this.eventSubscriber);
  }
  trackId(index: number, item: ISpeaker) {
    return item.id;
  }
  taskMain(data) {
    if (data == 1) {
      this.taskShow = false;
      this.taskTitel = '分值应填4-5分';
    } else if (data == 6 || data == 7 || data == 8) {
      this.taskShow = false;
      this.taskTitel = '分值应填1分';
    } else {
      this.taskShow = false;
      this.taskTitel = '分值应填2-3分';
    }
  }
  taskScoreMain(type, data) {
    if (type == 1) {
      if (data == 4 || data == 5) {
        this.taskShow = true;
        this.totalNumber = parseInt(data);
      } else {
        this.taskShow = false;
        this.taskTitel = '分值应填4-5分';
      }
    } else if (type == 6 || type == 7 || type == 8) {
      if (data == 1) {
        this.taskShow = true;
        this.totalNumber = parseInt(data);
      } else {
        this.taskShow = false;
        this.taskTitel = '分值应填1分';
      }
    } else {
      if (data == 2 || data == 3) {
        this.taskShow = true;
        this.totalNumber = parseInt(data);
      } else {
        this.taskShow = false;
        this.taskTitel = '分值应填2-3分';
      }
    }
  }

  discoveryMain(data) {
    if (data == 1) {
      this.discoveryShow = false;
      this.discoveryTitel = '分值应填21-30分';
    } else if (data == 2) {
      this.discoveryShow = false;
      this.discoveryTitel = '分值应填11-20分';
    } else {
      this.discoveryShow = false;
      this.discoveryTitel = '分值应填10分以下';
    }
  }
  discoveryScoreMain(type, data) {
    this.discoveryShow = true;
    this.totalNumber += parseInt(data);
  }

  levelMain(data) {
    if (data == 1) {
      this.levelShow = false;
      this.levelTitel = '分值应填21-30分';
    } else if (data == 2) {
      this.levelShow = false;
      this.levelTitel = '分值应填11-20分';
    } else {
      this.levelShow = false;
      this.levelTitel = '分值应填10分以下';
    }
  }
  levelScoreMain(type, data) {
    this.levelShow = true;
    this.totalNumber += parseInt(data);
  }

  applicationOptionsMain(data) {
    if (data == 1) {
      this.applicationShow = false;
      this.applicationTitel = '分值应填11-15分';
    } else if (data == 2) {
      this.applicationShow = false;
      this.applicationTitel = '分值应填6-10分';
    } else {
      this.applicationShow = false;
      this.applicationTitel = '分值应填5分或以下';
    }
  }
  applicationScoreMain(data) {
    this.applicationShow = true;
    this.totalNumber += parseInt(data);
  }
  paperScoreMain(data) {
    this.totalNumber += parseInt(data);
  }
  replyScoreMain(data) {
    this.totalNumber += parseInt(data);
  }

  registerChangeInSpeakers() {
    // this.eventSubscriber = this.eventManager.subscribe('speakerListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    // this.jhiAlertService.error(errorMessage, null, null);
  }
}
